package com.br.marcelo.pessoas.gateway.service;

import com.br.marcelo.pessoas.gateway.config.OpsUserDetails;
import com.br.marcelo.pessoas.gateway.entity.user.Usuario;
import com.br.marcelo.pessoas.gateway.gateway.repository.user.UserRepository;
import com.br.marcelo.pessoas.gateway.gateway.repository.user.UsuarioPermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UsuarioPermRepository usuarioPermRepository;

    @Override
    public OpsUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario user = repo.findByEmail(email);

        if (user == null) {
            return null;
        }

        List<String> roles = usuarioPermRepository.getRoles(email);

        Object[] rolesObj = roles.toArray();

        String[] rolesStr = new String[rolesObj.length];

        int loop = 0;
        for (Object item : roles.toArray()) {
            rolesStr[loop++] = item.toString();
        }

        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList(rolesStr);

        return new OpsUserDetails(user.getEmail(), user.getSenha(), auth, user);
    }

}