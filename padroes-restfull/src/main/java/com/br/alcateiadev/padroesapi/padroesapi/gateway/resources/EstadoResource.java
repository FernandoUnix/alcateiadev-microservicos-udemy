package com.br.alcateiadev.padroesapi.padroesapi.gateway.resources;

import com.br.alcateiadev.padroesapi.padroesapi.gateway.feign.EstadoClient;
import feign.Feign;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstadoResource {

    @Autowired
    private ErrorDecoder errorDecoder;

    @GetMapping(path = "/api/estados/")
    public ResponseEntity getEstados() {
        EstadoClient estadoClient = Feign.builder()
                .decoder(new GsonDecoder())
                .errorDecoder(errorDecoder)
                .target(EstadoClient.class, "http://localhost:8080/api/usuarios/");

        return new ResponseEntity<>(estadoClient.get(), HttpStatus.OK);
    }
}
