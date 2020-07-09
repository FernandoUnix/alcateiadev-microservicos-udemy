package com.br.marcelo.pessoas.app.repository.user;

import com.br.marcelo.pessoas.app.entity.user.UsuarioPerm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioPermRepository extends CrudRepository<UsuarioPerm, Long> {

    @Query(value = "select obj.role from UsuarioPerm obj where obj.email = ?1 ")
    List<String> getRoles(String email);
}
