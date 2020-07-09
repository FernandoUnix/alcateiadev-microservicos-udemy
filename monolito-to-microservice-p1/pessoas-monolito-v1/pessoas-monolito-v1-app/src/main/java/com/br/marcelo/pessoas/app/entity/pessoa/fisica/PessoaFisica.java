package com.br.marcelo.pessoas.app.entity.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.pessoa.DadosPessoais;
import com.br.marcelo.pessoas.app.entity.pessoa.EnderecoPessoaFisica;
import com.br.marcelo.pessoas.app.entity.pessoa.InformacaoPartidaria;
import com.br.marcelo.pessoas.app.entity.pessoa.OutrasInforEmails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "pessoa_fisica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisica {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Embedded
    private DadosPessoais dadosPessoais;

    @Embedded
    private EnderecoPessoaFisica enderecoPessoaFisica;

    @Transient
    private List<PessoaFisicaGrupo> grupos;

    @Transient
    private List<BigInteger> gruposId;

    @OneToMany(targetEntity = PessoaFisicaGrupo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa_fisica", insertable = false, updatable = false)
    private List<PessoaFisicaGrupo> pfGrupos;

    @Embedded
    private OutrasInforEmails outrasInforEmails;

    @Embedded
    private InformacaoPartidaria informacaoPartidaria;

    @Column(name = "facebook", length = 1000, nullable = true)
    private String facebook;

    @Column(name = "twitter", length = 1000, nullable = true)
    private String twitter;

    @Column(name = "youtube", length = 1000, nullable = true)
    private String youtube;

    @Column(name = "nomes_grupos", length = 1000, nullable = true)
    private String nomesGrupos;

    @Transient
    private transient String qtd;

    public PessoaFisica(Long id) {
        this.id = id;
    }
}
