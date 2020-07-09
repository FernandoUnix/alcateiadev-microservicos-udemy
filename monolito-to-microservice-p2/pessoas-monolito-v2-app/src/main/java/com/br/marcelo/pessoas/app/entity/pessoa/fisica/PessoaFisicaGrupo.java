package com.br.marcelo.pessoas.app.entity.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.grupo.Grupo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pessoa_fisica_grupo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaFisicaGrupo {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa_fisica", nullable = false, foreignKey = @ForeignKey(name = "FK_GRUPO_PESSOA_FISICA"))
    private PessoaFisica pessoaFisica;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false, foreignKey = @ForeignKey(name = "FK_GRUPO_PESSOA_FISICA_GRUPO"))
    private Grupo grupo;


}
