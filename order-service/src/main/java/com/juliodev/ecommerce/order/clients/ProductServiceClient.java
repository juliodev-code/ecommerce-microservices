package com.juliodev.ecommerce.order.clients;

import com.juliodev.ecommerce.order.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {

    final WebClient.Builder webClientBuilder;

    public ProductResponse getProductDetails(String id){
        return webClientBuilder.build()
                .get()
                .uri("http://PRODUCT-SERVICE/api/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }
}
