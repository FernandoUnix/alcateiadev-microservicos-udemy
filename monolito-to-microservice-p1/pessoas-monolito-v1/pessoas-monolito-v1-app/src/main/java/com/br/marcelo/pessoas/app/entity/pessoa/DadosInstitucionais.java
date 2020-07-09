package com.br.marcelo.pessoas.app.entity.pessoa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DadosInstitucionais {

    @Column(name = "razao_social", length = 1000, nullable = false)
    private String razaoSocial;

    @Column(name = "sigla", length = 10, nullable = false)
    private String sigla;

    @Column(name = "cnpj", length = 20, nullable = true)
    private String cnpj;

}
