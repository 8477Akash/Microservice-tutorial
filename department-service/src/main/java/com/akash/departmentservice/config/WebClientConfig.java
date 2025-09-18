package com.akash.departmentservice.config;

import com.akash.departmentservice.client.EmployeeClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    public WebClientConfig(LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction) {
        this.loadBalancedExchangeFilterFunction = loadBalancedExchangeFilterFunction;
    }

    @Bean
    public WebClient employeeWebClient() {
        return WebClient.builder()
                .baseUrl("http://employee-service") //-> need to add a filter to let webClient Know this is service registry
                .filter(loadBalancedExchangeFilterFunction)
                .build();

    }

    @Bean
    public EmployeeClient employeeClient() {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(employeeWebClient()))
                .build();

        return factory.createClient(EmployeeClient.class);
    }




}
