package com.br.marcelo.pessoas.app.enums;

public enum TipoEnderCorrespondencia {

	IMPESSOAL("I"),
	RESPONSAVEL("R"),
	CONTATO("C");
	
	private String valor;

	private TipoEnderCorrespondencia(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}

	public static TipoEnderCorrespondencia parse(String valor) {
		
		if( IMPESSOAL.toString().equals(valor) ){
			return IMPESSOAL;
		}
		
		if( RESPONSAVEL.toString().equals(valor) ){
			return RESPONSAVEL;
		}
		
		return CONTATO;
	}
}
