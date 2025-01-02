package com.first.function_module.service.integrated.impl;


import com.first.function_module.entity.IntegrationInfoEntity;
import com.first.function_module.repository.ModuleUrlRepository;
import com.first.function_module.service.integrated.ModuleUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ModuleUrlServiceImpl implements ModuleUrlService {

    private final ModuleUrlRepository moduleUrlRepository;

    @Override
    public String productUrlsubstitution(String name) {

        IntegrationInfoEntity byName = moduleUrlRepository.findByName(name);
        if (isNull(byName)) {
            return "Данного модуля не обнаружено";
        }

        return byName.getUrl();
    }



}