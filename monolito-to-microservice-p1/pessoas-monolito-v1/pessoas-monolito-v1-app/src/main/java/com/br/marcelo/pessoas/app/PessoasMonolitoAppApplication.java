package com.br.marcelo.pessoas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PessoasMonolitoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PessoasMonolitoAppApplication.class, args);
    }

    @RequestMapping("/status")
    public String hasWrite() {
        return "OK";
    }

}
