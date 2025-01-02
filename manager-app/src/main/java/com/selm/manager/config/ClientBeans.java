package com.selm.manager.config;

import com.selm.manager.client.RestClientProductsRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientProductsRestClient productsRestClient(
            @Value("${selm.cervices.catalogue.uri:http://localhost:8081}") String catalogueBaseUri,
            @Value("${selm.cervices.catalogue.username:}") String catalogueUserName,
            @Value("${selm.cervices.catalogue.password:}") String cataloguePassword){
        return new RestClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(new BasicAuthenticationInterceptor(catalogueUserName, cataloguePassword))
                .build());
    }
}
