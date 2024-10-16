package com.first.function_module.controller;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.service.CheckValidTokenService;
import com.first.function_module.service.RatingService;
import com.first.function_module.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.first.function_module.controller.ControllerConstants.*;


@RestController
@RequiredArgsConstructor
public class ApartmentController {

    private final RentService rentService;
    private final RatingService ratingService;
    private final CheckValidTokenService checkValidTokenService;


    @PostMapping(SEARCH_APART_BY_PARAM)
    public List<ApartmentDto> searchApartment(@RequestParam(required = false) Double countOfPeople,
                                              @RequestParam(required = false) Double area,
                                              @RequestParam(required = false) Double cost) {

        return rentService.searchApartment(countOfPeople, area, cost);
    }

    @PostMapping(RENT_APART)
    public String rentApartment(@RequestBody ApartmentDto apartmentDto) {

        return rentService.rentApartment(apartmentDto);
    }

    @PostMapping("/register_apartment")
    public String registerApartment(@RequestHeader String token,
                                    @RequestBody ApartmentDto apartmentDto) {
        UserInfoEntity userInfoEntity = checkValidTokenService.checkTokenForVAlid(token);
        return rentService.registerApartment(apartmentDto, userInfoEntity);
    }

    @GetMapping(ADD_THE_COMMENT)
    public String addTheComment(@RequestParam Long apartmentID,
                                @RequestParam Integer rating,
                                @RequestParam(required = false) String comments) {
        return ratingService.addRating(apartmentID, rating, comments);
    }

    @GetMapping("/show")
    public ApartmentDto showApartment(@RequestParam Long id) {

        return rentService.showApartment(id);
    }

    @PostMapping("/addphoto")
    public String AddPhoto(@RequestParam Long id, @RequestParam MultipartFile multipartFile) throws IOException {

        return rentService.addphoto(id, multipartFile);
    }


//    @GetMapping("/apartment")
//    public List<ApartmentDto> apartment (@RequestParam Integer id){
//       return apartmentService.showApartment(id);
//    }
}

