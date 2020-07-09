package com.br.marcelo.pessoas.app.entity.pessoa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contato {

	@Column(name = "nome_contato", length = 1000, nullable = true)
	private String nome;

	public Contato() {
	}

	public Contato(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
