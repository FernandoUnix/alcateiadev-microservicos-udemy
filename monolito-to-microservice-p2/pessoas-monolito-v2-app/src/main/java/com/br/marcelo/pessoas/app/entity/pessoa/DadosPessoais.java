package com.br.marcelo.pessoas.app.entity.pessoa;

import com.br.marcelo.pessoas.app.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoais {

    @Column(name = "tratamento", length = 1000, nullable = true)
    private String tratamento;

    @Column(name = "nome", length = 1000, nullable = false)
    private String nome;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sexo", length = 20, nullable = true)
    private Sexo sexo;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dtnascimento", nullable = true)
    private Date dtNascimento;

    @Column(name = "criterio", length = 1000, nullable = true)
    private String criterio;

    @Column(name = "apelido", length = 1000, nullable = true)
    private String apelido;


    public DadosPessoais(String nome) {
        this.nome = nome;
    }
}
