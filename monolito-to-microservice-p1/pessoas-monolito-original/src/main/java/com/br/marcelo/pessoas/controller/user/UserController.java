package com.br.marcelo.pessoas.controller.user;

import com.br.marcelo.pessoas.entity.user.Usuario;
import com.br.marcelo.pessoas.entity.user.UsuarioPerm;
import com.br.marcelo.pessoas.json.UsuarioJson;
import com.br.marcelo.pessoas.repository.user.UserRepository;
import com.br.marcelo.pessoas.repository.user.UsuarioPermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsuarioPermRepository usuarioPermRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void incluir(@Valid @NotNull @RequestBody UsuarioJson json)
            throws Exception {

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();

        Usuario usuario = Usuario
                .builder()
                .email(json.getEmail())
                .flAdmin(true)
                .flLerEsc(true)
                .habilitado(true)
                .nome(json.getNome())
                .senha(crypt.encode(json.getSenha()))
                .build();

        userRepository.save(usuario);

        usuarioPermRepository.save(UsuarioPerm
                .builder()
                .email(json.getEmail())
                .role("ROLE_USER")
                .build()
        );
    }

}
