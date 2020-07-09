package com.br.marcelo.pessoas.app.service.cargo;

import com.br.marcelo.pessoas.app.entity.cargo.Cargo;
import org.springframework.data.domain.Page;

public interface CargoService {

	void incluir(Cargo bean) throws Exception;

	void alterar(Cargo bean) throws Exception;

	void excluir(Long id) throws Exception;

	Page<Cargo> consulta(Cargo bean, int pos, String ordenacao) throws Exception;

	Cargo getById(Long id);

	Page<Cargo> consultaComponente(String codigo, String nome, Long ignorar, Integer pos);

}
