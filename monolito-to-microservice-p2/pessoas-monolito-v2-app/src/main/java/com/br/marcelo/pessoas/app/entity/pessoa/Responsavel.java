package com.br.marcelo.pessoas.app.entity.pessoa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Responsavel {

	@Column(name = "nome_responsavel", length = 1000, nullable = true)
	private String nome;
	
	@Column(name = "tratamento_responsavel", length = 1000, nullable = true)
	private String tratamento;
	
	@Column(name = "posicao_responsavel", length = 1000, nullable = true)
	private String posicao;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name = "dtposse_responsavel", nullable = true)
	private Date dtPosse;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public Date getDtPosse() {
		return dtPosse;
	}

	public void setDtPosse(Date dtPosse) {
		this.dtPosse = dtPosse;
	}
	
	
}
