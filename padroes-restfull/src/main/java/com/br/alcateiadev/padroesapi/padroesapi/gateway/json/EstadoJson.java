package com.br.alcateiadev.padroesapi.padroesapi.gateway.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class EstadoJson implements Serializable {

    private Long id;
    private Long sigla;
    private String nome;

}