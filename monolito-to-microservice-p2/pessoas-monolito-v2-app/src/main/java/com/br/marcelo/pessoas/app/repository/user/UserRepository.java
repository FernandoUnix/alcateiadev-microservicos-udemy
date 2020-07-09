package com.br.marcelo.pessoas.app.repository.user;

import com.br.marcelo.pessoas.app.entity.user.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Usuario, Long> {

    @Query("from Usuario obj  where obj.email = ?1")
    Usuario findByEmail(String email);
}