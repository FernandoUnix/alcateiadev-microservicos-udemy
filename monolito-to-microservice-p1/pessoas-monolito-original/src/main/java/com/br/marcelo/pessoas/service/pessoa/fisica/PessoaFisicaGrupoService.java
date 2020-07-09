package com.br.marcelo.pessoas.service.pessoa.fisica;

import java.util.List;

import com.br.marcelo.pessoas.entity.pessoa.fisica.PessoaFisica;
import com.br.marcelo.pessoas.entity.pessoa.fisica.PessoaFisicaGrupo;

public interface PessoaFisicaGrupoService {

	void incluir(PessoaFisica bean, List<PessoaFisicaGrupo> grupos);

	void alterar(PessoaFisica bean, List<PessoaFisicaGrupo> grupos);

	void excluir(PessoaFisica bean);

	List<PessoaFisicaGrupo> getGrupos(PessoaFisica bean);

}
