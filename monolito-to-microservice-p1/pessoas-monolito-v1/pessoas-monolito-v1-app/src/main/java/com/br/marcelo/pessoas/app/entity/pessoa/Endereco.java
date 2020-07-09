package com.br.marcelo.pessoas.app.entity.pessoa;

import com.br.marcelo.pessoas.app.enums.TipoEnderCorrespondencia;
import com.br.marcelo.pessoas.app.entity.localizacao.SisCidade;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Endereco {

    @Column(name = "logradouro", length = 1000, nullable = false)
    private String logradouro;

    @Column(name = "bairro", length = 1000, nullable = false)
    private String bairro;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", nullable = false, foreignKey = @ForeignKey(name = "FK_PESSOA_JURIDICA_CIDADE"))
    private SisCidade cidade;

    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tp_ender_corresp", length = 20, nullable = false)
    private TipoEnderCorrespondencia tipoEnderCorrespondencia;

    @Column(name = "tel_principal", length = 50, nullable = false)
    private String telefonePrincipal;

    @Column(name = "tel_secundario", length = 50, nullable = true)
    private String telefoneSecundario;

    @Column(name = "fax", length = 50, nullable = true)
    private String fax;

    @Column(name = "tel_outro", length = 50, nullable = true)
    private String telefoneOutro;

}
