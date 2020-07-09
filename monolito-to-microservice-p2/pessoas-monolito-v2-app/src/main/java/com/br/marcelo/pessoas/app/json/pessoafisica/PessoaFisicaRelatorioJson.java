package com.br.marcelo.pessoas.app.json.pessoafisica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaRelatorioJson {

    @NotNull(message = "Tipo de Relatório é obrigatório")
    @NotEmpty(message = "Tipo de Relatório é obrigatório")
    private String tipoRelatorio;

    @NotNull(message = "Ordenador Por é obrigatório")
    @NotEmpty(message = "Ordenador Por é obrigatório")
    private String ordenadoPor;

    @NotNull(message = "Ordenador Em é obrigatório")
    @NotEmpty(message = "Ordenador Em é obrigatório")
    private String ordenadoEm;

    private String semGrupos;

    private String nome;
    private String sexo;
    private Long regionalEstado;
    private Long regional;

    private Long estado;
    private Long cidade;

    private Long grupo;
    private Long cargo;
    private Long partido;

    private Long profissao;


}
