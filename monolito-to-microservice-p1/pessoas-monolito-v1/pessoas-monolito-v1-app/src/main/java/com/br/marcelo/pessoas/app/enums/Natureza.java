package com.br.marcelo.pessoas.app.enums;

public enum Natureza {

	PUBLICA("P"),
	PUBLICA_NAO_PUBLICA("PN"),
	NAO_PUBLICA("N"),
	NAO_SE_APLICA("NA");
	
	private String valor;

	private Natureza(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
	
}
