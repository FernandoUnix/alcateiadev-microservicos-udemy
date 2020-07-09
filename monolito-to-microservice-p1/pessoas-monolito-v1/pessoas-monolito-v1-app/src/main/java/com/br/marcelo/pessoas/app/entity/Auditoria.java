package com.br.marcelo.pessoas.app.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class Auditoria {

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "dtinclusao", nullable = true)
    private Date dtInclusao;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "dtalteracao", nullable = true)
    private Date dtAlteracao;

    @Column(name = "usuario_inclusao", length = 1000, nullable = true)
    private String usuarioInclusao;

    @Column(name = "usuario_alteracao", length = 1000, nullable = true)
    private String usuarioAlteracao;

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }


}
