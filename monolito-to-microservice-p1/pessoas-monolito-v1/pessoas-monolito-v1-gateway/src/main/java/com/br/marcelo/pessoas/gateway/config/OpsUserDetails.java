package com.br.marcelo.pessoas.gateway.config;

import com.br.marcelo.pessoas.gateway.entity.user.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class OpsUserDetails extends User {

    private static final long serialVersionUID = 3375988537129735478L;
    private String nomeUsuario;
    private Usuario usuario;

    public OpsUserDetails(String login, String senha, List<GrantedAuthority> auth, Usuario usuario) {
        super(login, senha, auth);
        this.nomeUsuario = usuario.getNome();
        this.usuario = usuario;
    }

}
