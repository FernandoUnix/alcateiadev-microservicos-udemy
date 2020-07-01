package com.br.alcateiadev.padroesapi.padroesapi.exceptios;

import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NomeInvalidoException.class)
    public ResponseEntity nomeInvalido() {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setCodigo(100);
        errorMsg.setMsg("Nome inválido");

        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity handleFeignStatusException(RetryableException e, HttpServletResponse response) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setCodigo(100);
        errorMsg.setMsg(e.getMessage());

        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
