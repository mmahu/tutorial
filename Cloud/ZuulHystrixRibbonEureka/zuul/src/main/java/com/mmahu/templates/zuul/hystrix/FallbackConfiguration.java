package com.mmahu.templates.zuul.hystrix;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Component
public class FallbackConfiguration {

    @Bean
    public FallbackProvider workerFallbackProvider() {
        return new FallbackProvider() {

            @Override
            public String getRoute() {
                return "worker";
            }

            @Override
            public ClientHttpResponse fallbackResponse(final Throwable cause) {
                if (cause instanceof HystrixTimeoutException) {
                    return response(HttpStatus.GATEWAY_TIMEOUT);
                } else {
                    return fallbackResponse();
                }
            }

            @Override
            public ClientHttpResponse fallbackResponse() {
                return response(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            private ClientHttpResponse response(final HttpStatus status) {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return status;
                    }

                    @Override
                    public int getRawStatusCode() throws IOException {
                        return status.value();
                    }

                    @Override
                    public String getStatusText() throws IOException {
                        return status.getReasonPhrase();
                    }

                    @Override
                    public void close() {
                    }

                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream("Worker(s) not available!".getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        return headers;
                    }
                };
            }
        };
    }

}
