package com.br.marcelo.pessoas.app.entity.localizacao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sis_pais")
@Data
public class SisPais implements Serializable {

    @Id
    @Column(name = "id_pais", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "nome", length = 1000, nullable = false)
    private String nome;

    @Column(name = "codigo_ibge", nullable = false)
    private Long codigoIbge;


}
