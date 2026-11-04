package com.juliodev.ecommerce.order.clients;

import com.juliodev.ecommerce.order.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

    final WebClient.Builder webClientBuilder;

    public UserResponse getUserDetails(String id){
        return webClientBuilder.build()
                .get()
                .uri("http://user-service/api/users/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }

}
