package com.br.marcelo.pessoas.gateway.exception;

public class RoleException extends RuntimeException {

    public RoleException(){
        super("Você não tem permissão para acessar este recurso.");
    }

}
