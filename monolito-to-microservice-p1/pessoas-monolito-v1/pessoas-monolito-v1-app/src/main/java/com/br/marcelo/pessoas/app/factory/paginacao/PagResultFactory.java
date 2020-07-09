package com.br.marcelo.pessoas.app.factory.paginacao;

import java.util.List;

import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import org.springframework.data.domain.Page;


public class PagResultFactory {

	private static final PagResultFactory INSTANCE = new PagResultFactory();
	
	private PagResultFactory() {
	}

	public static PagResultFactory getInstance(){
		return INSTANCE;
	}
	
	public PagResultJson create(Page result, List lista){
		
		PagResultJson json = new PagResultJson(lista, result.getSize(), result.getTotalPages(), result.getNumber(), result.getTotalElements());
		
		return json;
	}
}
