package com.juliodev.ecommerce.order.clients;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Autowired(required = false)
    private ObservationRegistry observationRegistry;

    @Autowired(required = false)
    private Tracer tracer;

    @Autowired(required = false)
    private Propagator propagator;


    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {

        WebClient.Builder builder = WebClient.builder();

        if(observationRegistry != null){
            if (observationRegistry != null) {
                builder.filter(createTracingFilter());
            }
        }

        return builder;
    }

    private ExchangeFilterFunction createTracingFilter() {
        return (request, next) -> {
            if (tracer != null && propagator != null && tracer.currentSpan() != null) {
                ClientRequest newRequest = ClientRequest.from(request)
                        .headers(headers -> propagator.inject(
                                tracer.currentTraceContext().context(),
                                headers,
                                HttpHeaders::add))
                        .build();
                return next.exchange(newRequest);
            }
            return next.exchange(request);
        };
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
