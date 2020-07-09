package com.br.marcelo.pessoas.app.json.pessoafisica;

import com.br.marcelo.pessoas.app.json.DateJson;
import com.br.marcelo.pessoas.app.json.ResumoSaveJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaJson extends ResumoSaveJson {

    private Long id;

    private Long codigo;

    private String nome;

    private String extra;

    private String qtd;

    @Size(max = 1000, message = "Tratamento deve conter no máximo 1000 caracteres.")
    private String dpTratamento;

    @NotNull(message = "Nome é obrigatório.")
    @NotEmpty(message = "Nome é obrigatório.")
    @Size(max = 1000, message = "Nome deve conter no máximo 1000 caracteres.")
    private String dpNome;

    private String dpSexo;

    private DateJson dpDtNascimento;

    @Size(max = 1000, message = "Critério deve conter no máximo 1000 caracteres.")
    private String dpCriterio;

    @Size(max = 1000, message = "Apelido deve conter no máximo 1000 caracteres.")
    private String dpApelido;

    @NotNull(message = "Logradouro é obrigatório.")
    @NotEmpty(message = "Logradouro é obrigatório.")
    @Size(max = 1000, message = "Logradouro deve conter no máximo 1000 caracteres.")
    private String enderLogradouro;

    @NotNull(message = "Bairro é obrigatório.")
    @NotEmpty(message = "Bairro é obrigatório.")
    @Size(max = 1000, message = "Bairro deve conter no máximo 1000 caracteres.")
    private String enderBairro;

    private Long enderEstadoId;
    private String enderEstadoNome;
    private String enderEstadoUf;

    @NotNull(message = "CEP é obrigatório.")
    @NotEmpty(message = "CEP é obrigatório.")
    @Size(max = 10, message = "CEP deve conter no máximo 1000 caracteres.")
    private String enderCep;

    private String enderTipoEndereco;

    private String enderCorrespondencia;

    @Size(max = 50, message = "Telefone principal deve conter no máximo 50 caracteres.")
    private String enderTelefonePrincipal;

    @Size(max = 50, message = "Telefone secundário deve conter no máximo 50 caracteres.")
    private String enderTelefoneSecundario;

    @Size(max = 50, message = "Telefone celular deve conter no máximo 50 caracteres.")
    private String enderFax;

    @Size(max = 50, message = "Telefone outro deve conter no máximo 50 caracteres.")
    private String enderTelefoneOutro;

    @Size(max = 1000, message = "Correspondencia deve conter no máximo 1000 caracteres.")
    private String enderEncaminCorrespondencia;

    @Size(max = 5000, message = "Outras informações devem conter no máximo 5000 caracteres.")
    private String outrasInformacoes;

    @Size(max = 1000, message = "E-Mail deve conter no máximo 1000 caracteres.")
    private String email;

    @Size(max = 5000, message = "E-Mail outros devem conter no máximo 5000 caracteres.")
    private String emailOutros;

    private Long profissaoId;
    private String profissaoNome;
    private String profissaoCodigo;

    private Long partidoId;
    private String partidoNome;
    private String partidoCodigo;

    @Size(max = 50, message = "Título de eleitor deve conter no máximo 50 caracteres.")
    private String tituloEleitor;

    @Size(max = 10, message = "Sessão deve conter no máximo 10 caracteres.")
    private String sessao;

    @Size(max = 10, message = "Zona deve conter no máximo 10 caracteres.")
    private String zona;

    @Size(max = 1000, message = "Facebook deve conter no máximo 1000 caracteres.")
    private String facebook;

    @Size(max = 1000, message = "Twitter deve conter no máximo 1000 caracteres.")
    private String twitter;

    @Size(max = 1000, message = "Youtube deve conter no máximo 1000 caracteres.")
    private String youtube;

    private List<PessoaFisicaGrupoJson> grupos;

    private String enderecoCompleto;


}
