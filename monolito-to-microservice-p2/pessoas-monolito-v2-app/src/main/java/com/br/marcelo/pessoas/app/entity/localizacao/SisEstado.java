package com.br.marcelo.pessoas.app.entity.localizacao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sis_estado")
@Data
public class SisEstado implements Serializable {

    @Id
    @Column(name = "id_estados", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @Column(name = "nome", length = 1000, nullable = false)
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais", nullable = false)
    private SisPais pais;

    @Column(name = "codigo_ibge", nullable = false)
    private Long codigoIbge;


}
