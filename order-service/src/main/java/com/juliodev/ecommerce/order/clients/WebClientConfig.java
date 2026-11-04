package com.juliodev.ecommerce.order.clients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

//    @Bean
//    public ProductServiceClient restClientInterface(WebClient.Builder restClientBuilder) {
//        System.out.println("Passed here in ProductServiceClientConfig");
//        WebClient restClient = restClientBuilder
//                //.baseUrl("http://product-service")
//                .baseUrl("http://PRODUCT-SERVICE") // Use the service name and port of the product-service
//                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
//                        ((request, response) -> Optional.empty()))
//                .build();
//        RestClientAdapter adapter = RestClientAdapter.create(restClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory
//                .builderFor(adapter)
//                .build();
//        return factory.createClient(ProductServiceClient.class);
//    }


}
