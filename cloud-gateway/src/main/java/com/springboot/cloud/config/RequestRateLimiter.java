package com.springboot.cloud.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 多个维度限流：ip/请求路径/username
 */
@Component
public class RequestRateLimiter {

    /**
     * ip地址限流
     *
     * @return 限流key
     */
    @Bean
    @Primary
    public KeyResolver remoteAddressKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
    }

    /**
     * 请求路径限流
     *
     * @return 限流key
     */
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * username限流
     *
     * @return 限流key
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("username")));
    }

}
