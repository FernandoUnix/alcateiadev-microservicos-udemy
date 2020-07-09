package com.br.marcelo.pessoas.service.cargo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.br.marcelo.pessoas.entity.cargo.Categoria;

public interface CategoriaService {

	void incluir(Categoria categoria) throws Exception;

	void alterar(Categoria categoria) throws Exception;

	void excluir(Long id) throws Exception;

	Page<Categoria> consulta(Categoria categoria, int pos, String ordenacao) throws Exception;

	Categoria getById(Long id);

	List<Categoria> consultaComponente(Categoria categoria) throws Exception;

}
