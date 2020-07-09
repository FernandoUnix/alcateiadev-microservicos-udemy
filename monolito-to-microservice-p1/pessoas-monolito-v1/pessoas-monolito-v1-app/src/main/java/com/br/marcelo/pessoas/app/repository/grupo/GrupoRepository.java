package com.br.marcelo.pessoas.app.repository.grupo;

import com.br.marcelo.pessoas.app.entity.grupo.Grupo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GrupoRepository extends CrudRepository<Grupo, Long>, JpaSpecificationExecutor<Grupo> {

    @Query("select count(obj.id) from Grupo obj where obj.codigo = ?1 ")
    Long getCodigo(String codigo);

    @Query("select count(obj.id) from Grupo obj where obj.codigo = ?1 and obj.id <> ?2  ")
    Long getCodigo(String codigo, Long id);
}
