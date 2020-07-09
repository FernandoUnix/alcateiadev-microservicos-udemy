package com.br.marcelo.pessoas.app.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "email", length = 1000, nullable = false)
    private String email;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "fl_habilitado", nullable = false)
    private Boolean habilitado;

    @Column(name = "admin", nullable = false)
    private Boolean flAdmin;

    @Column(name = "fl_ler_esc", nullable = true)
    private Boolean flLerEsc;

    @Column(name = "senha", length = 1000, nullable = false)
    private String senha;
}