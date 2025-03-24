package com.first.function_module.service.impl;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.UserInfoDto;
import com.first.function_module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.first.function_module.service.impl.AuthServiceImpl.USER_EXIST_EXEPTION;
import static com.first.function_module.service.impl.AuthServiceImpl.YOU_ARE_REGISTERD;
import static com.first.function_module.service.test_util_methods.AuthServiceUtilsTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrationUserTest() {

        UserInfoDto userInfoMethod = getUserInfoMethod();

        UserInfoEntity userEntityMethod = getUserEntityMethodWithUser();

       UserInfoDto userInfoDtoWithNull = getUserMethodWithOutFoundedUser();

        when(userRepository.findUserInfoEntityByNickname(userInfoMethod.getNickname())).thenReturn(userEntityMethod);
        when(userRepository.findUserInfoEntitiesByEmail(userInfoMethod.getEmail())).thenReturn(userEntityMethod);

        String resultNegotive = authService.registrationUser(userInfoMethod);
        String resultPositive = authService.registrationUser(userInfoDtoWithNull);

        assertEquals(USER_EXIST_EXEPTION, resultNegotive);
        assertEquals(YOU_ARE_REGISTERD,resultPositive);
        verify(userRepository).save(any(UserInfoEntity.class));

    }

}