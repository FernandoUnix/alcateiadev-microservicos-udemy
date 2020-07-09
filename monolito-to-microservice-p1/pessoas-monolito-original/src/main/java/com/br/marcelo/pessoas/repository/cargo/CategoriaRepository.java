package com.br.marcelo.pessoas.repository.cargo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.br.marcelo.pessoas.entity.cargo.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>, JpaSpecificationExecutor<Categoria> {

}
