package com.example.rent_sender.service.impl;

import com.example.rent_sender.model.UserInfoEntity;
import com.example.rent_sender.repository.IntegrationRepository;
import com.example.rent_sender.repository.UserRepository;
import com.example.rent_sender.service.RealizationOfEmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealizationOfEmailSenderImpl implements RealizationOfEmailSender {

    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;
    private final IntegrationRepository integrationRepository;


    private final static String TEXT_TO_VERIFICATION = "уважаемый пользователь потвердите свой емейл \n ";
    private final static String SUBJEXT_OF_EMAIL_TO_VERIFICATION = "потвердите ваш емейл \n";



    @Override
     public void verifyEmailSender(String userlogin) {
        UserInfoEntity userForSend = userRepository.findUserByEmailJpql(userlogin);

            emailService.emailVerification(userForSend.getEmail(),SUBJEXT_OF_EMAIL_TO_VERIFICATION,TEXT_TO_VERIFICATION
                    +urlGenerator(userForSend.getLogin(),userForSend.getVerification()));

    }
    private String urlGenerator(String login, String stringCode) {

        return String.format(integrationRepository.findUrlByName("rent-sender_url_verificaion").getUrl(),login,stringCode);
    }

}
