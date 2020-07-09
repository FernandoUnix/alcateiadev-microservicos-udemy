package com.br.marcelo.pessoas.service;

import com.br.marcelo.pessoas.config.OpsUserDetails;
import com.br.marcelo.pessoas.entity.user.Usuario;
import com.br.marcelo.pessoas.repository.user.UserRepository;
import com.br.marcelo.pessoas.repository.user.UsuarioPermRepository;
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