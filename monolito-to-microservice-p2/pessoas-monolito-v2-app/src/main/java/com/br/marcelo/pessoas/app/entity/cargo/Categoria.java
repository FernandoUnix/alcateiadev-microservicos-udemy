package com.br.marcelo.pessoas.app.entity.cargo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cargo_categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {


    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "nome", length = 1000, nullable = false)
    private String nome;


    public Categoria(Long categoria) {
        this.id = categoria;
    }
}
