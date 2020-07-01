package com.br.alcateiadev.padroesapi.padroesapi.config;

import com.br.alcateiadev.padroesapi.padroesapi.exceptios.FeignExceptionDecode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FeignConfig {

    private static final String UTF_8 = "UTF-8";

    @Bean
    public ErrorDecoder errorDecoder() {
        return (String s, Response response) -> {
            try {
                if (response.body() != null) {
                    String message = IOUtils.toString(response.body().asInputStream(), UTF_8);
                    ObjectMapper mapper = new ObjectMapper();
                    return new FeignExceptionDecode(response.status());
                } else {
                    System.out.println("errorDecoder: "+s);
                }
                return new FeignExceptionDecode(response.status(), response.reason());
            } catch (IOException ex) {
                return new FeignExceptionDecode(response.status(), s);
            }
        };
    }

}
