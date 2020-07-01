package com.br.alcateiadev.padroesapi.padroesapi.gateway.resources;

import com.br.alcateiadev.padroesapi.padroesapi.entity.UsuarioEntity;
import com.br.alcateiadev.padroesapi.padroesapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioResource {

    @GetMapping(path = "/api/usuarios/")
    public ResponseEntity consultarUsuarios() {
        UsuarioEntity user = new UsuarioEntity();
        user.setId(1L);
        user.setName("Marcelo");
        List<UsuarioEntity> users = new ArrayList<>();
        users.add(user);

        return new ResponseEntity<>(users, HttpStatus.BAD_REQUEST);
    }

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/api/usuarios/")
    public ResponseEntity incluirUsuarios(@RequestBody UsuarioEntity user) {
        System.out.println("Olá " + user.getName());

        usuarioService.incluirUsuario(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/api/usuarios/{id}")
    public void alterarUsuario(@RequestBody UsuarioEntity user,
                               @PathVariable("id") String idUser) {

        System.out.println("O usuário com o código " + idUser +
                " será alterado o nome para " + user.getName());

    }

    @DeleteMapping(path = "/api/usuarios/{id}")
    public ResponseEntity deletarUsuario(@PathVariable("id") String idUser) {
        System.out.println("O usuário com o código " + idUser +
                " será deletado ");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
