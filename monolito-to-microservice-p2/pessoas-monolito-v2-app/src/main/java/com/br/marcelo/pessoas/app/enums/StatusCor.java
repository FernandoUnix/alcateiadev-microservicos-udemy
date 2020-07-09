package com.br.marcelo.pessoas.app.enums;

public enum StatusCor {

    VERMELHO("#F44336"),
    PRETO("#000000"),
    AMARELO("#FFC107"),
    AZUL("#2196F3"),
    VERDE("#4CAF50"),
    CINZA("#9E9E9E");

    private String value;

    StatusCor(String value) {
        this.value = value;
    }

    public static StatusCor getCod(String value) {

        for (StatusCor cor : values()) {
            if( value.equals(cor.value)){
                return cor;
            }
        }

        return null;
    }

    public String getValue(){
        return this.value;
    }
}
