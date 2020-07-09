package com.br.marcelo.pessoas.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateJson {

    private String data;
    private String dataAmericana;

    public DateJson(Date dtNascimento) {

    }
}
