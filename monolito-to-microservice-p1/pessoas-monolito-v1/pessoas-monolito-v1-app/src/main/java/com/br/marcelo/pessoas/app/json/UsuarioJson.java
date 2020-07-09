package com.br.marcelo.pessoas.app.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioJson implements Serializable {

    private Long id;

    @NotNull(message = "E-Mail é obrigatório")
    @NotEmpty(message = "E-Mail é obrigatório")
    @Email
    private String email;

    @NotNull(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    private String senha;

}
