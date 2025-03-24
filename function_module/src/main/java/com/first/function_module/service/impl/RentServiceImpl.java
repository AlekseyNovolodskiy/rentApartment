package com.first.function_module.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.function_module.config.KafkaTopics;
import com.first.function_module.entity.*;
import com.first.function_module.exception.ApartmentException;
import com.first.function_module.exception.EmailSenderException;
import com.first.function_module.exception.UserException;
import com.first.function_module.mapper.RentApartmentMapper;
import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.model.dto.RentApartmentDto;
import com.first.function_module.model.dto.UserProductIntegrationDto;
import com.first.function_module.repository.*;
import com.first.function_module.repository.dao.ApartmentDao;
import com.first.function_module.service.RentService;
import com.first.function_module.service.UserDiscountService;
import com.first.function_module.service.excel.ExcelResponce;
import com.first.function_module.service.integrated.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.first.function_module.service.impl.RatingServiceImpl.RATING_MESSAGE_EXCEPTION;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class RentServiceImpl implements RentService {

    private final UserPublicRepository userPublicRepository;
    private Logger logger = LoggerFactory.getLogger(RentServiceImpl.class);

    private final IntegrationService integrationService;
    private final RatingRepository ratingRepository;
    private final ApartmentRepository apartmentRepository;
    private final RentApartmentMapper mapper;
    private final ApartmentDao apartmentDao;
    private final AddressRepository addressRepository;
    private final PhotoRepository photoRepository;
    private final BookingRepository bookingRepository;
    private final ExcelResponce excelResponce;
    private final UserDiscountService userDiscountService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public static final String APARTMENTS_NOT_FOUND = "апартаментов по условию поиска не обнаружено";
    public static final String APARTMENT_IS_BUSY = "Время для бронирования занято";
    public static final String APARTMENT_DOUBLE_BOOKING_BY_USER = "вы не можете зарегистрировать более одного апартамента";
    public static final String FILL_ACCOUNT = "Дополните данные о поездке и своем акканте";

// критерия билдер
//    private List<ApartmentEntity> findApartmentByCriteria(double countOfPeoplecrit){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ApartmentEntity> query = criteriaBuilder.createQuery(ApartmentEntity.class);
//        Root<ApartmentEntity> root = query.from(ApartmentEntity.class);
//        query.select(root)
//                .where(criteriaBuilder.equal(root.get("countOfPeople"),countOfPeoplecrit));
//        List<ApartmentEntity> resultList = entityManager.createQuery(query).getResultList();
//        return resultList;
//    }

    @Override
    public List<ApartmentDto> searchApartment(Integer countOfPeople, Double area, Double cost) {

// поиск по количеству людей
        if (!isNull(countOfPeople) && isNull(area) && isNull(cost)) {

            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesWithCountOfPeopleJPQL(countOfPeople);
            apartmentDao.findApartmentByCountOfPeople(countOfPeople);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }


// поиск по AREA
        if (isNull(countOfPeople) && !isNull(area) && isNull(cost)) {

            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByArea(area);
//            apartmentEntities.isEmpty()
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

// поиск по стоимости
        if (isNull(countOfPeople) && isNull(area) && !isNull(cost)) {

            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCost(cost);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

// поиск по трем показателям
        if (!isNull(countOfPeople) && !isNull(area) && !isNull(cost)) {

            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCostAndAreaAndCountOfPeople(cost, area, countOfPeople);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

//поиск по площади и стоимости
        if (isNull(countOfPeople) && !isNull(area) && !isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByAreaAndCost(area, cost);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

//поиск по количеству людей и стоимости
        if (!isNull(countOfPeople) && isNull(area) && !isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCountOfPeopleAndCost(countOfPeople, cost);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

// посик поколичеству людей и площади
        if (!isNull(countOfPeople) && !isNull(area) && isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCountOfPeopleAndArea(countOfPeople, area);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }
        return null;
    }


    private List<ApartmentDto> mapListentityToApartmnetDTO(List<ApartmentEntity> apartmentEntities) {

        return mapper.apartmentEntityToApartmentDto(apartmentEntities);
    }


    public ApartmentDto showApartment(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ApartmentException(RATING_MESSAGE_EXCEPTION, 600));
        List<RateEntity> rateList = ratingRepository.findRateEntitiesByApartmentEntity(apartment);

        ApartmentDto apartmentDto = mapper.apartmentEntityToApartmentDtowithRating(apartment);

        int temp = 0;

        for (RateEntity r : rateList) {
            temp = temp + r.getRating();
        }

        double avg = temp / rateList.size();
        apartmentDto.setAvgRate(avg);

        return apartmentDto;
    }

    @Override
    public List<ApartmentDto> checkByLocation(String latitude, String longitude) {

        logger.info("function_module.checkByLocation - > https://api.opencagedata.com/");

        String city = integrationService.getIntegrationByGeoLocation(latitude, longitude).getResults().get(0).getComponents().getCity();

        logger.info("function_module.checkByLocation < - https://api.opencagedata.com/");

        List<ApartmentEntity> apartmentEntities = apartmentRepository.findByCity(city);

        List<ApartmentDto> apartmentDtos = mapper.apartmentEntityToApartmentDto(apartmentEntities);

        return apartmentDtos;
    }


    @Override
    public String addphoto(Long id, MultipartFile multipartFile) throws IOException {

        ApartmentEntity apartmentEntity = apartmentRepository.findById(id).get();
        PhotoEntity photoEntity = new PhotoEntity((multipartFile.getBytes()), apartmentEntity);
        photoRepository.save(photoEntity);

        return "фото добавлено";
    }


    @Override
    public String rentApartment(RentApartmentDto rentApartmentDto, UserInfoEntity userInfoEntity) {
        ApartmentEntity apartmentById = apartmentRepository.findApartmentById(rentApartmentDto.getApartmentId());

        List<BookingEntity> bookingEntitiesByApartmentID = bookingRepository.findBookingEntitiesByApartmentID(rentApartmentDto.getApartmentId());
        if (!isNull(bookingEntitiesByApartmentID)) {
            for (BookingEntity bookingEntity : bookingEntitiesByApartmentID) {

                if (bookingEntity.getUserInfoEntity().equals(userInfoEntity)
                        && bookingEntity.getApartmentEntity().equals(apartmentById)) {
                    throw new ApartmentException(APARTMENT_DOUBLE_BOOKING_BY_USER, 600);
                }

                int startDay = bookingEntity.getTimeOfStartRent().getDayOfYear();
                int endDay = bookingEntity.getTimeOfStartRent().getDayOfYear() + bookingEntity.getBookingDays();
                int startApartmentBooking = rentApartmentDto.getTimeOfStartRent().getDayOfYear();
                int endApartmentBooking = rentApartmentDto.getTimeOfStartRent().getDayOfYear() + rentApartmentDto.getTimeOfRent();

                if ((startApartmentBooking >= startDay && endApartmentBooking <= endDay)
                        || (endApartmentBooking >= startDay && endApartmentBooking <= endDay)
                        || (startApartmentBooking <= endDay && endApartmentBooking >= endDay)) {
                    throw new ApartmentException(APARTMENT_IS_BUSY, 600);
                }
            }
        }

        if (isNull(apartmentById)) {
            throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
        }

        try {
            //сохраняем в базу букинг зарезервированные апартаменты
            bookingRepository.save(prepareBookingEntityToSave(rentApartmentDto, userInfoEntity));

            //расчитываем скидку для пользователся
            userDiscountService.discountInjetion(getPublicUserDto(userInfoEntity, rentApartmentDto));

            //извещение пользователя
            integrationService.getIntegrationWithEmailBookingNotification(userInfoEntity);

            //сохряняем данные о резервировании апартаментов в файле excel
            saveBookingToExcel(rentApartmentDto, userInfoEntity);

        } catch (HttpServerErrorException e) {
            // todo
            throw new EmailSenderException("Рассылка на данный момент не доступна");

        } catch (Exception e) {

            kafkaTemplate.send(KafkaTopics.PRODUCT.getTopicName(), convertDtoToStringRentService(getPublicUserDto(userInfoEntity, rentApartmentDto)));
            integrationService.getIntegrationWithEmailRejectedBokkingNotification(userInfoEntity);

        }


        // сохрание данных об аренде в файл эксель
//        saveBookingToExcel(rentApartmentDto, userInfoEntity);

        return "Апартаменты зарезервированы";
    }

    private void saveBookingToExcel(RentApartmentDto rentApartmentDto, UserInfoEntity userInfoEntity) {

        ApartmentEntity apartmentById = apartmentRepository.findApartmentById(rentApartmentDto.getApartmentId());


        BookingEntity byUserandApartment = bookingRepository.findByUser(userInfoEntity, apartmentById);


        excelResponce.prepareBookingReport("city is " + apartmentById.getAddressEntity().getCity() + ", street is " +
                        apartmentById.getAddressEntity().getStreet() + ", building is " +
                        apartmentById.getAddressEntity().getBuildingNumber(),
                rentApartmentDto.getTimeOfRent(),
                userInfoEntity.getEmail(),
                byUserandApartment.getSum());
    }

    private BookingEntity prepareBookingEntityToSave(RentApartmentDto rentApartmentDto, UserInfoEntity userInfoEntity) {

        ApartmentEntity apartmentById = apartmentRepository.findApartmentById(rentApartmentDto.getApartmentId());

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setSum(apartmentById.getCost() * rentApartmentDto.getTimeOfRent());
        bookingEntity.setBookingDays(rentApartmentDto.getTimeOfRent());
        bookingEntity.setUserInfoEntity(userInfoEntity);
        bookingEntity.setTimeOfStartRent(rentApartmentDto.getTimeOfStartRent());
        bookingEntity.setTimeOfRegistration(LocalDateTime.now());
        bookingEntity.setApartmentEntity(apartmentById);

        return bookingEntity;
    }

    private UserProductIntegrationDto getPublicUserDto(UserInfoEntity userInfoEntity, RentApartmentDto rentApartmentDto) {

        ApartmentEntity apartmentById = apartmentRepository.findApartmentById(rentApartmentDto.getApartmentId());

        BookingEntity bookingEntity = bookingRepository.findByUser(userInfoEntity, apartmentById);

        UserPublicEntity publicUserEntity = userPublicRepository
                .findByUser(userInfoEntity.getId())
                .orElseThrow(() -> new UserException(FILL_ACCOUNT));

        UserProductIntegrationDto userProductIntegrationDto = new UserProductIntegrationDto();


        // Форматирование с временной зоной
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterJustDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        userProductIntegrationDto.setBirthDay(publicUserEntity.getBirthday().format(formatterJustDay));
        userProductIntegrationDto.setLargeFamily(publicUserEntity.getLargefamily());
        userProductIntegrationDto.setOnVacation(publicUserEntity.getOnVacation());
        userProductIntegrationDto.setBussines(publicUserEntity.getBussinestrip());
        userProductIntegrationDto.setStudent(publicUserEntity.getStudent());
        userProductIntegrationDto.setTimeOfRent(bookingEntity.getBookingDays());
        userProductIntegrationDto.setTimeOfRegistration(bookingEntity.getTimeOfRegistration().format(formatterWithTime));
        userProductIntegrationDto.setUserId(bookingEntity.getUserInfoEntity().getId());
        userProductIntegrationDto.setApartmentId(bookingEntity.getApartmentEntity().getId());


        return userProductIntegrationDto;
    }

    private String convertDtoToStringRentService(UserProductIntegrationDto userProductIntegrationDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(userProductIntegrationDto);
        } catch (JsonProcessingException e) {
            System.out.println(" Ошибка преобразования в Json");
            throw new RuntimeException();
        }

    }

    private void prepareReport() {

        File file = new File("C:\\Users\\alex\\Downloads\\rentApartment\\rentApartment\\excel_template.xlsx");

        List<AddressEntity> cityList = addressRepository.findbyCity("Москва");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheetAt = workbook.getSheetAt(0);
            int countOfRow = 1;
            for (AddressEntity a : cityList) {
                Row row = sheetAt.createRow(countOfRow++);
                row.createCell(0).setCellValue(a.getCity() + " ," + a.getStreet());
                row.createCell(1).setCellValue(a.getBuildingNumber());
                row.createCell(2).setCellValue(a.getCity());
                row.createCell(3).setCellValue(a.getBuildingNumber());

            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);

            fileOutputStream.flush();

            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("Проблема с выгрузкой отчета");
        }
    }


    @Override
    public String registerApartment(ApartmentDto apartmentDto, UserInfoEntity userInfoEntity) {


        ApartmentEntity apartmentEntity = apartmentRepository
                .findByCostAndAreaAndCountOfPeople
                        (apartmentDto.getCost(), apartmentDto.getArea(), apartmentDto.getCountOfPeople());
        if (!isNull(apartmentEntity)) {
            return "апартаменты уже существуют";
        }

        ApartmentEntity apartmentToSave = new ApartmentEntity();

        apartmentToSave.setArea(apartmentDto.getArea());
        apartmentToSave.setCost(apartmentDto.getCost());
        apartmentToSave.setCountOfPeople(apartmentDto.getCountOfPeople());
        apartmentToSave.setDescription(apartmentDto.getDescription());


        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(apartmentDto.getCity());
        addressEntity.setStreet(apartmentDto.getStreet());
        addressEntity.setBuildingNumber(apartmentDto.getBuildingNumber());
        addressEntity.setApartmentEntity(apartmentToSave);

        apartmentRepository.save(apartmentToSave);
        addressRepository.save(addressEntity);

        return userInfoEntity.getNickname() + " вы зарегистрировали новые апартаменты";

    }


}
