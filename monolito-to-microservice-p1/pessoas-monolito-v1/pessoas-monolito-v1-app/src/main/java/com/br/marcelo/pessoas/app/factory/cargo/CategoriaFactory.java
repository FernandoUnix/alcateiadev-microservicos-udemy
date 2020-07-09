package com.br.marcelo.pessoas.app.factory.cargo;

import java.util.ArrayList;
import java.util.List;

import com.br.marcelo.pessoas.app.entity.cargo.Categoria;
import com.br.marcelo.pessoas.app.json.cargo.CategoriaJson;

public class CategoriaFactory {

	private static final CategoriaFactory INSTANCE = new CategoriaFactory();
	
	private CategoriaFactory() {
	}

	public static CategoriaFactory getInstance(){
		return INSTANCE;
	}

	public Categoria create(CategoriaJson json) {
		
		Categoria categoria = new Categoria();
		
		if( json == null ){
			return categoria;
		}
		
		categoria.setId(json.getId());
		categoria.setNome(json.getNome());
		
		return categoria;
	}

	public List<CategoriaJson> create(List<Categoria> list) {
		
		List<CategoriaJson> nList = new ArrayList<CategoriaJson>();
		for(Categoria item : list){
			nList.add(create(item));
		}
		
		return nList;
	}

	public CategoriaJson create(Categoria item) {
		
		CategoriaJson json = new CategoriaJson();
		json.setNome(item.getNome());
		json.setId(item.getId());
		
		return json;
	}
}
