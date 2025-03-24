package com.module.product_module.scheduler;

import com.module.product_module.config.ProductTopicListenerConfig;
import com.module.product_module.entity.IntegrationInfoEntity;
import com.module.product_module.model.dto.UserProductIntegrationDto;
import com.module.product_module.repository.IntegrationRepository;
import com.module.product_module.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ProductScheduler {

    private final Logger logger = LoggerFactory.getLogger(ProductScheduler.class);

    private final IntegrationRepository integrationRepository;
    private final ProductTopicListenerConfig productTopicListenerConfig;
    private final ProductService productService;

    private final RestTemplate restTemplatet = new RestTemplate();

    @Scheduled(fixedDelay = 6000)
    private void checkValidToken() {

        List<UserProductIntegrationDto> messagesList = productTopicListenerConfig.getMessagesList();
        int size = messagesList.size();
        System.out.println();
        logger.info("В очереди на обработку " + size + " сообщений");
        System.out.println();
        for (UserProductIntegrationDto message : messagesList) {
            log.info("message is "+ message);
            restTemplatet.exchange(prepareUrlToRequest(),
                    HttpMethod.POST,
                    prepareHttpToRequest(message),
                    String.class);
            messagesList.removeLast();
        }


    }

    private String prepareUrlToRequest() {

        Optional<IntegrationInfoEntity> discountResponceAutomatical = integrationRepository.findUrlByName("discountResponceAutomatical");
        IntegrationInfoEntity integrationInfoEntity = discountResponceAutomatical.orElseThrow(() -> new RuntimeException("Url not found"));
        return integrationInfoEntity.getUrl();
    }

    private HttpEntity prepareHttpToRequest(UserProductIntegrationDto userProductIntegrationDto) {
        String stringValue = productService.requestDiscountToFunctionalModul(userProductIntegrationDto);

        HttpEntity http = new HttpEntity(stringValue, null);
        return http;
    }
//    String discountValue = productService.requestDiscountToFunctionalModul(userProductIntegrationDto);
}
