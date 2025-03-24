package com.example.rent_sender.service.impl;

import com.example.rent_sender.model.UserDto;
import com.example.rent_sender.repository.IntegrationRepository;
import com.example.rent_sender.service.RealizationOfEmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealizationOfEmailSenderImpl implements RealizationOfEmailSender {

    private final EmailServiceImpl emailService;
    private final IntegrationRepository integrationRepository;

    private final static String TEXT_TO_VERIFICATION = "уважаемый пользователь потвердите свой емейл \n ";
    private final static String SUBJEXT_OF_EMAIL_TO_VERIFICATION = "потвердите ваш емейл \n";
    private final static String SUBJECT_OF_EMAIL_BOOKING_NOTIFICATION = "регистрация апартаментов";
    private final static String TEXT_TO_BOKKING_NOTIFICATION = "уважаемый %s вы успешно зарегистрировали апартаменты ";
    private final static String SUBJECT_OF_REJECTED_BOOKING_NOTIFICATION = "регистрация недоступна";
    private final static String TEXT_TO_REJECTED_BOOKING_NOTIFICATION = "уважаемый %s апартаменты  зарегистрировать не удалось ";


    @Override
    public void verifyEmailSender(UserDto userDto) {

        emailService.emailVerification(userDto.getEmail(), SUBJEXT_OF_EMAIL_TO_VERIFICATION, TEXT_TO_VERIFICATION
                + urlGeneratorForVerification(userDto.getLogin(), userDto.getCode()));
    }

    @Override
    public void bookingNotation(UserDto userDto) {

        emailService.emailVerification(userDto.getEmail(),SUBJECT_OF_EMAIL_BOOKING_NOTIFICATION,String.format(TEXT_TO_BOKKING_NOTIFICATION,userDto.getLogin()));
    }

    @Override
    public void rejectedbookingNotation(UserDto userDto) {
        emailService.emailVerification(userDto.getEmail(), SUBJECT_OF_REJECTED_BOOKING_NOTIFICATION,String.format(TEXT_TO_REJECTED_BOOKING_NOTIFICATION,userDto.getLogin()));
    }


    private String urlGeneratorForVerification(String login, String stringCode) {

        String verificationText = String.format(integrationRepository.findUrlByName("rent-sender_url_verificaion")
                , login
                , stringCode);
        return verificationText;
    }

}
