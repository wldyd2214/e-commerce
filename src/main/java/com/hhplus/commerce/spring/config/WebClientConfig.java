package com.hhplus.commerce.spring.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {

    @Value("${payment-gateway.base-url}")
    private String paymentGatewayBaseUrl;
    @Value("${payment-gateway.mid}")
    private String paymentGatewayMid;
    @Value("${payment-gateway.client-key}")
    private String paymentGatewayClientKey;

    @Bean
    public WebClient payVerseWebClient(WebClient.Builder builder) {

        HttpClient httpClient = HttpClient.create(connectionProvider());

        return builder.clientConnector(new ReactorClientHttpConnector(httpClient))
            .baseUrl(paymentGatewayBaseUrl)
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("mid", paymentGatewayMid)
            .defaultHeader("clientKey", paymentGatewayClientKey)
            .build();
    }

    private ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("http-pool")
            .maxConnections(50)                             // 커넥션 풀 최대 수
            .maxIdleTime(Duration.ofSeconds(60))            // 커넥션 풀의 커넥션 최대 유휴 시간
            .maxLifeTime(Duration.ofSeconds(60))            // 커넥션 풀의 커넥션 최대 생명 시간
            .pendingAcquireTimeout(Duration.ofSeconds(60))  // 커넥션 풀에서 커넥션 획득 시 가질 수 있는 최대 대기 시간
            .pendingAcquireMaxCount(-1)                     // 커넥션 풀에서 커넥션 획득 시 최대 대기 수 (-1 : 무제한)
            .evictInBackground(Duration.ofSeconds(30))      // 백그라운드에서 유휴 커넥션을 해제하는 주기적인 작업 간격
            .build();
    }
}
