package com.br.marcelo.pessoas.service.cargo;

import org.springframework.data.domain.Page;

import com.br.marcelo.pessoas.entity.cargo.Cargo;

public interface CargoService {

	void incluir(Cargo bean) throws Exception;

	void alterar(Cargo bean) throws Exception;

	void excluir(Long id) throws Exception;

	Page<Cargo> consulta(Cargo bean, int pos, String ordenacao) throws Exception;

	Cargo getById(Long id);

	Page<Cargo> consultaComponente(String codigo, String nome, Long ignorar, Integer pos);

}
