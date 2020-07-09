package br.com.ops.opscadastro.comuns.enums;

public enum RedisGeraChaves {

	BEM_VINDO,
	PERFIL_USUARIO,
	CONDOMINIO;
	
	public String getChave(String valor){
		return this.toString().concat("_").concat(valor);
	}
	
}
