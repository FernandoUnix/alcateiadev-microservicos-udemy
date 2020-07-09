package com.br.marcelo.pessoas.app.repository.cargo;

import com.br.marcelo.pessoas.app.entity.cargo.Cargo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CargoRepository extends CrudRepository<Cargo, Long>, JpaSpecificationExecutor<Cargo> {

    @Query("select count(obj.id) from Cargo obj where obj.codigo = ?1  ")
    Long getCodigo(String codigo);

    @Query("select count(obj.id) from Cargo obj where obj.codigo = ?1 and obj.id <> ?2   ")
    Long getCodigo(String codigo, Long id);

    @Query("from Cargo obj where obj.codigo like ?1 or obj.nome  like ?1  ")
    List<Cargo> getComponente(String nome);

    @Query("from Cargo obj where (obj.codigo like ?1 or obj.nome  like ?1) and obj.id <> ?2  ")
    List<Cargo> getComponenteComIgnorar(String nome, Long ignorar);

}
