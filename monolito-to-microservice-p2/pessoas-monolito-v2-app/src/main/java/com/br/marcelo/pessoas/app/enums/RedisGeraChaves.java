package com.br.marcelo.pessoas.app.enums;

public enum RedisGeraChaves {

	BEM_VINDO,
	PERFIL_USUARIO,
	CONDOMINIO;
	
	public String getChave(String valor){
		return this.toString().concat("_").concat(valor);
	}
	
}
