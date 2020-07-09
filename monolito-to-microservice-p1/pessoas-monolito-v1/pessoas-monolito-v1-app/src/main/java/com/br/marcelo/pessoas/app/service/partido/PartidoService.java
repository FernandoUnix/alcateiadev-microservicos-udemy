package com.br.marcelo.pessoas.app.service.partido;

import com.br.marcelo.pessoas.app.entity.partido.Partido;
import org.springframework.data.domain.Page;

public interface PartidoService {

	void incluir(Partido partido) throws Exception;

	void alterar(Partido partido) throws Exception;

	void excluir(Long id) throws Exception;

	Page<Partido> consulta(Partido partido, int pos, String ordenacao) throws Exception;

	Partido getById(Long id);

	Page<Partido> consultaComponente(String codigo, String nome, Long ignorar,
			int pos);

}
