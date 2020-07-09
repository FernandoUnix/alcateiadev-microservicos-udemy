package com.br.marcelo.pessoas.entity.pessoa;

import br.com.ops.opscadastro.comuns.enums.Correspondencia;
import br.com.ops.opscadastro.comuns.enums.TipoEndereco;
import com.br.marcelo.pessoas.entity.localizacao.SisCidade;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class EnderecoPessoaFisica {

    @Column(name = "logradouro", length = 1000, nullable = false)
    private String logradouro;

    @Column(name = "bairro", length = 1000, nullable = false)
    private String bairro;


    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_endereco", length = 20, nullable = true)
    private TipoEndereco tipoEndereco;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "correspondencia", length = 20, nullable = true)
    private Correspondencia correspondencia;

    @Column(name = "tel_principal", length = 50, nullable = true)
    private String telefonePrincipal;

    @Column(name = "tel_secundario", length = 50, nullable = true)
    private String telefoneSecundario;

    @Column(name = "fax", length = 50, nullable = true)
    private String fax;

    @Column(name = "tel_outro", length = 50, nullable = true)
    private String telefoneOutro;

    @Column(name = "encamin_correso", length = 1000, nullable = true)
    private String encaminCorrespondencia;
}
