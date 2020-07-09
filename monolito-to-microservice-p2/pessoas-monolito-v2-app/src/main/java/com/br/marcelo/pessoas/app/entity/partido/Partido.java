package com.br.marcelo.pessoas.app.entity.partido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "partido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partido {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "sigla", length = 10, nullable = false)
    private String sigla;

    @Column(name = "descricao", length = 1000, nullable = false)
    private String descricao;


    public Partido(Long partidoId) {
        this.id = partidoId;
    }
}
