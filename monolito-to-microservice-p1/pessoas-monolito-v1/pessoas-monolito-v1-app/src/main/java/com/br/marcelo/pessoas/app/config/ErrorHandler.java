package com.br.marcelo.pessoas.app.config;

import com.br.marcelo.pessoas.app.json.ExceptionJson;
import com.br.marcelo.pessoas.app.exception.DeleteRuntimeException;
import com.br.marcelo.pessoas.app.exception.OpsException;
import com.br.marcelo.pessoas.app.exception.OpsRuntimeException;
import com.br.marcelo.pessoas.app.exception.RoleException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = RoleException.class)
    public ResponseEntity<ExceptionJson> tratarErroRole(RoleException e, HttpServletResponse response) {
        ExceptionJson json = new ExceptionJson();
        json.setMessage(e.getMessage());

        return new ResponseEntity<ExceptionJson>(json, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = DeleteRuntimeException.class)
    public ResponseEntity<ExceptionJson> tratarErroConstraint(DeleteRuntimeException e, HttpServletResponse response) {

        ExceptionJson json = new ExceptionJson();
        json.setMessage("Registro não pode ser excluído porque existem registro dependentes.");

        return new ResponseEntity<ExceptionJson>(json, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionJson> tratarErro(MethodArgumentNotValidException e, HttpServletResponse response) {

        e.printStackTrace();

        ExceptionJson json = new ExceptionJson();
        json.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<ExceptionJson>(json, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionJson> tratarErro(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        ExceptionJson json = new ExceptionJson();
        json.setMessage(e.getMessage());

        if (e instanceof DataIntegrityViolationException) {
            json.setMessage("Registro não pode ser excluído porque existem registro dependentes.");
        }

        if (e instanceof DeleteRuntimeException) {
            json.setMessage("Registro não pode ser excluído porque existem registro dependentes.");
        }

        return new ResponseEntity<ExceptionJson>(json, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OpsRuntimeException.class)
    public ResponseEntity<ExceptionJson> tratarErro(OpsRuntimeException e, HttpServletResponse response) {
        ExceptionJson json = new ExceptionJson();
        json.setMessage(e.getMessage());
        return new ResponseEntity<ExceptionJson>(json, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OpsException.class)
    public ResponseEntity<ExceptionJson> tratarErro(OpsException e, HttpServletResponse response) {
        ExceptionJson json = new ExceptionJson();
        json.setMessage(e.getMessage());
        return new ResponseEntity<ExceptionJson>(json, HttpStatus.BAD_REQUEST);
    }

}
