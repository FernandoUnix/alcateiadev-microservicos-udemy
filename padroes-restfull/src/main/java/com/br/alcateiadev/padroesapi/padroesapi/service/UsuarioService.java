package com.br.alcateiadev.padroesapi.padroesapi.service;

import com.br.alcateiadev.padroesapi.padroesapi.entity.UsuarioEntity;
import com.br.alcateiadev.padroesapi.padroesapi.exceptios.NomeInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public void incluirUsuario(UsuarioEntity user){
        if (user.getName().length() > 10) {
            throw new NomeInvalidoException();
        }
    }

}
