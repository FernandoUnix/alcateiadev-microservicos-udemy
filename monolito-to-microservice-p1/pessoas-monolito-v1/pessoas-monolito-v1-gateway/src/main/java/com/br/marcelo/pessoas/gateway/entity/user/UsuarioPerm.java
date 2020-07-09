package com.br.marcelo.pessoas.gateway.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "usuario_perm")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPerm {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "email", length = 1000, nullable = false)
    private String email;

    @Column(name = "role", length = 1000, nullable = false)
    private String role;

}
