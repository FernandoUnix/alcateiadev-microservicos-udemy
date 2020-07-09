package com.br.marcelo.pessoas.app.entity.grupo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grupo")
@AllArgsConstructor
@NoArgsConstructor
public class Grupo {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "codigo", length = 5, nullable = false)
    private String codigo;

    @Column(name = "nome", length = 1000, nullable = false)
    private String nome;

    @Column(name = "comentario", length = 1000, nullable = true)
    private String comentario;


    public Grupo(Long id) {
        this.id = id;
    }
}
