package com.br.marcelo.pessoas.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegionalJson {

    @NotNull(message = "Código é obrigatório")
    @NotEmpty(message = "Código é obrigatório")
    private Long codigo;

    @NotNull(message = "Tipo é obrigatório")
    @NotEmpty(message = "Tipo é obrigatório")
    private String tipo;

    private String nome;

}
