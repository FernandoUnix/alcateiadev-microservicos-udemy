package com.br.marcelo.pessoas.app.entity.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.br.marcelo.pessoas.app.entity.partido.Partido;

@Embeddable
public class InformacaoPartidaria {

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_partido", nullable = true, foreignKey = @ForeignKey(name = "FK_PESSOA_FISICA_PARTIDO"))
	private Partido partido;

	@Column(name = "titulo_eleitor", length = 50, nullable = true)
	private String tituloEleitor;

	@Column(name = "sessao", length = 10, nullable = true)
	private String sessao;

	@Column(name = "zona", length = 10, nullable = true)
	private String zona;

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}
