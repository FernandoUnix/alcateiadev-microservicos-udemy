package com.br.marcelo.pessoas.app.entity.localizacao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sis_cidade")
@Data
public class SisCidade implements Serializable {

    @Id
    @Column(name = "id_cidade", unique = true)
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private SisEstado estado;

    @Column(name = "nome", length = 1000, nullable = false)
    private String nome;

    @Column(name = "tipo", length = 1, nullable = true)
    private String tipo;

    @Column(name = "meso", nullable = true)
    private Long meso;

    @Column(name = "micro", nullable = true)
    private Long micro;

    @Column(name = "codigo_ibge", nullable = true)
    private Long codigoIbge;

    @Column(name = "distr", nullable = true)
    private Long distr;

}
