package com.br.marcelo.pessoas.app.factory.partido;

import java.util.ArrayList;
import java.util.List;

import com.br.marcelo.pessoas.app.entity.partido.Partido;
import com.br.marcelo.pessoas.app.json.partido.PartidoJson;


public class PartidoFactory {

	private static final PartidoFactory INSTANCE = new PartidoFactory();
	
	private PartidoFactory() {
	}

	public static PartidoFactory getInstance(){
		return INSTANCE;
	}

	public PartidoJson create(Partido bean) {
		
		PartidoJson json = new PartidoJson();
		json.setId(bean.getId());
		json.setSigla(bean.getSigla());
		json.setDescricao(bean.getDescricao());
		
		json.setCodigo(bean.getSigla());
		json.setNome(bean.getDescricao());
		
		return json;
	}

	public List<PartidoJson> create(List<Partido> list) {
		
		List<PartidoJson> lista = new ArrayList<PartidoJson>();
		
		for(Partido item : list){
			lista.add(create(item));
		}
		
		return lista;
	}

	public Partido create(PartidoJson json) {
		
		Partido bean = new Partido();
		bean.setId(json.getId());
		bean.setSigla(json.getSigla());
		bean.setDescricao(json.getDescricao());
		
		return bean;
	}
	
	
	
	
}
