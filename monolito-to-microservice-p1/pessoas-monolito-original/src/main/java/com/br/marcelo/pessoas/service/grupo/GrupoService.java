package com.br.marcelo.pessoas.service.grupo;

import org.springframework.data.domain.Page;

import com.br.marcelo.pessoas.entity.grupo.Grupo;

public interface GrupoService {

	void incluir(Grupo grupo) throws Exception;

	void alterar(Grupo grupo) throws Exception;

	void excluir(Long id) throws Exception;

	Page<Grupo> consulta(Grupo grupo, int pos, String ordenacao) throws Exception;

	Grupo getById(Long id);

	Page<Grupo> consultaComponente(String codigo, String nome,
			Long ignorar, Integer parseInt);

}
