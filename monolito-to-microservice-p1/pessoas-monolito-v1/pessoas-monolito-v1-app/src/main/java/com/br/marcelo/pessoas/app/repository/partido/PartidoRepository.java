package com.br.marcelo.pessoas.app.repository.partido;

import com.br.marcelo.pessoas.app.entity.partido.Partido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PartidoRepository extends CrudRepository<Partido, Long>, JpaSpecificationExecutor<Partido> {

    @Query("select count(obj.id) from Partido obj where obj.sigla = ?1  ")
    Long getSigla(String sigla);

    @Query("select count(obj.id) from Partido obj where obj.sigla = ?1 and obj.id <> ?2   ")
    Long getSigla(String sigla, Long id);

}
