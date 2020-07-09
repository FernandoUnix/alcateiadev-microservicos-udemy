package com.br.marcelo.pessoas.app.entity.pessoa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OutrasInforEmails {

	@Column(name = "outras_informacoes", length = 1000, nullable = true)
	private String outrasInformacoes;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "email_outros", length = 1000, nullable = true)
	private String emailOutros;

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailOutros() {
		return emailOutros;
	}

	public void setEmailOutros(String emailOutros) {
		this.emailOutros = emailOutros;
	}

}
