package com.br.marcelo.pessoas.app.factory.pessoafisica;

import java.util.ArrayList;
import java.util.List;

import com.br.marcelo.pessoas.app.entity.grupo.Grupo;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisicaGrupo;
import com.br.marcelo.pessoas.app.json.grupo.GrupoJson;
import com.br.marcelo.pessoas.app.json.pessoafisica.PessoaFisicaGrupoJson;

public class PessoaFisicaGrupoFactory {

	private static final PessoaFisicaGrupoFactory INSTANCE = new PessoaFisicaGrupoFactory();

	private PessoaFisicaGrupoFactory() {
	}

	public static PessoaFisicaGrupoFactory getInstance() {
		return INSTANCE;
	}

	public List<PessoaFisicaGrupoJson> create(List<PessoaFisicaGrupo> grupos) {
		
		List<PessoaFisicaGrupoJson> list = new ArrayList<PessoaFisicaGrupoJson>();
		if( grupos == null ){
			return list;
		}
		
		for (PessoaFisicaGrupo grupo : grupos) {
			list.add(create(grupo));
		}
		
		return list;
	}

	private PessoaFisicaGrupoJson create(PessoaFisicaGrupo grupo) {

		PessoaFisicaGrupoJson json = new PessoaFisicaGrupoJson();
		json.setId(grupo.getId());
		json.setGrupo(new GrupoJson(grupo.getGrupo().getId(), grupo.getGrupo().getCodigo(), grupo.getGrupo().getNome(), ""));
		
		return json;
	}

	public List<PessoaFisicaGrupo> createJsonToBean(List<PessoaFisicaGrupoJson> jsons) {

		List<PessoaFisicaGrupo> list = new ArrayList<PessoaFisicaGrupo>();
		if( jsons == null ){
			return list;
		}
		
		for (PessoaFisicaGrupoJson json : jsons) {
			list.add(create(json));
		}
		
		return list;
	}

	private PessoaFisicaGrupo create(PessoaFisicaGrupoJson json) {

		PessoaFisicaGrupo bean = new PessoaFisicaGrupo();
		
		bean.setId(json.getId());
		bean.setGrupo(new Grupo(json.getGrupo().getId()));
		
		return bean;
	}


}
