package com.br.marcelo.pessoas.app.factory.pessoafisica;

import com.br.marcelo.pessoas.app.enums.Correspondencia;
import com.br.marcelo.pessoas.app.enums.Sexo;
import com.br.marcelo.pessoas.app.enums.TipoEndereco;
import com.br.marcelo.pessoas.app.entity.partido.Partido;
import com.br.marcelo.pessoas.app.entity.pessoa.DadosPessoais;
import com.br.marcelo.pessoas.app.entity.pessoa.EnderecoPessoaFisica;
import com.br.marcelo.pessoas.app.entity.pessoa.InformacaoPartidaria;
import com.br.marcelo.pessoas.app.entity.pessoa.OutrasInforEmails;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import com.br.marcelo.pessoas.app.json.DateJson;
import com.br.marcelo.pessoas.app.json.DateJsonUtils;
import com.br.marcelo.pessoas.app.json.pessoafisica.PessoaFisicaJson;
import com.br.marcelo.pessoas.app.json.pessoafisica.PessoaFisicaMiniJson;

import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaFactory {

    private static final PessoaFisicaFactory INSTANCE = new PessoaFisicaFactory();

    private PessoaFisicaFactory() {
    }

    public static PessoaFisicaFactory getInstance() {
        return INSTANCE;
    }

    public PessoaFisicaJson create(PessoaFisica bean) {

        PessoaFisicaJson pf = new PessoaFisicaJson();
        pf.setId(bean.getId());
        pf.setCodigo(bean.getCodigo());
        pf.setNome(bean.getDadosPessoais().getNome());

        if (bean.getInformacaoPartidaria().getPartido() != null) {
            pf.setExtra(bean.getInformacaoPartidaria().getPartido().getDescricao());
        }

        pf.setDpTratamento(bean.getDadosPessoais().getTratamento());
        pf.setDpNome(bean.getDadosPessoais().getNome());

        if (bean.getDadosPessoais().getSexo() != null) {
            pf.setDpSexo(bean.getDadosPessoais().getSexo().toString());
        }

        if (bean.getDadosPessoais().getDtNascimento() != null) {
            pf.setDpDtNascimento(new DateJson(bean.getDadosPessoais()
                    .getDtNascimento()));
        }

        pf.setDpCriterio(bean.getDadosPessoais().getCriterio());
        pf.setDpApelido(bean.getDadosPessoais().getApelido());
        pf.setEnderLogradouro(bean.getEnderecoPessoaFisica().getLogradouro());
        pf.setEnderBairro(bean.getEnderecoPessoaFisica().getBairro());


        StringBuilder enderecoCompleto = new StringBuilder();
        enderecoCompleto.append(bean.getEnderecoPessoaFisica().getLogradouro());
        enderecoCompleto.append(", ");
        enderecoCompleto.append(bean.getEnderecoPessoaFisica().getBairro());

        pf.setEnderecoCompleto(enderecoCompleto.toString());

        pf.setEnderCep(bean.getEnderecoPessoaFisica().getCep());

        if (bean.getEnderecoPessoaFisica().getTipoEndereco() != null) {
            pf.setEnderTipoEndereco(bean.getEnderecoPessoaFisica()
                    .getTipoEndereco().toString());
        }

        if (bean.getEnderecoPessoaFisica().getCorrespondencia() != null) {
            pf.setEnderCorrespondencia(bean.getEnderecoPessoaFisica()
                    .getCorrespondencia().toString());
        }

        pf.setEnderTelefonePrincipal(bean.getEnderecoPessoaFisica().getTelefonePrincipal());
        pf.setEnderTelefoneSecundario(bean.getEnderecoPessoaFisica()
                .getTelefoneSecundario());
        pf.setEnderFax(bean.getEnderecoPessoaFisica().getFax());
        pf.setEnderTelefoneOutro(bean.getEnderecoPessoaFisica()
                .getTelefoneOutro());
        pf.setEnderEncaminCorrespondencia(bean.getEnderecoPessoaFisica()
                .getEncaminCorrespondencia());
        pf.setOutrasInformacoes(bean.getOutrasInforEmails()
                .getOutrasInformacoes());
        pf.setEmail(bean.getOutrasInforEmails().getEmail());
        pf.setEmailOutros(bean.getOutrasInforEmails().getEmailOutros());

        if (bean.getInformacaoPartidaria().getPartido() != null) {
            pf.setPartidoId(bean.getInformacaoPartidaria().getPartido().getId());
            pf.setPartidoNome(bean.getInformacaoPartidaria().getPartido()
                    .getDescricao());
            pf.setPartidoCodigo(bean.getInformacaoPartidaria().getPartido()
                    .getSigla());
        }

        pf.setTituloEleitor(bean.getInformacaoPartidaria().getTituloEleitor());
        pf.setSessao(bean.getInformacaoPartidaria().getSessao());
        pf.setZona(bean.getInformacaoPartidaria().getZona());
        pf.setFacebook(bean.getFacebook());
        pf.setTwitter(bean.getTwitter());

        pf.setGrupos(PessoaFisicaGrupoFactory.getInstance().create(
                bean.getGrupos()));

        pf.setYoutube(bean.getYoutube());

        return pf;
    }

    public List<PessoaFisicaJson> createNomeQtd(List<PessoaFisica> list) {

        List<PessoaFisicaJson> listJson = new ArrayList<PessoaFisicaJson>();

        if (list == null) {
            return listJson;
        }

        for (PessoaFisica pessoaFisica : list) {
            PessoaFisicaJson pfj = new PessoaFisicaJson();
            pfj.setNome(pessoaFisica.getDadosPessoais().getNome());
            pfj.setQtd(pessoaFisica.getQtd());
            listJson.add(pfj);
        }

        return listJson;
    }

    public List<PessoaFisicaJson> create(List<PessoaFisica> list) {

        List<PessoaFisicaJson> listJson = new ArrayList<PessoaFisicaJson>();

        if (list == null) {
            return listJson;
        }

        for (PessoaFisica pessoaFisica : list) {
            listJson.add(create(pessoaFisica));
        }

        return listJson;
    }

    public PessoaFisica create(PessoaFisicaJson json) {

        PessoaFisica bean = new PessoaFisica();

        bean.setId(json.getId());
        bean.setCodigo(json.getCodigo());

        bean.setDadosPessoais(new DadosPessoais());
        bean.getDadosPessoais().setTratamento(json.getDpTratamento());
        bean.getDadosPessoais().setNome(json.getDpNome());

        if (json.getDpSexo() != null) {
            bean.getDadosPessoais().setSexo(Sexo.valueOf(json.getDpSexo()));
        }

        if (json.getDpDtNascimento() != null) {
            bean.getDadosPessoais().setDtNascimento(
                    DateJsonUtils.valueOf(json.getDpDtNascimento().getData()));
        }

        bean.getDadosPessoais().setCriterio(json.getDpCriterio());
        bean.getDadosPessoais().setApelido(json.getDpApelido());

        bean.setEnderecoPessoaFisica(new EnderecoPessoaFisica());
        bean.getEnderecoPessoaFisica().setLogradouro(json.getEnderLogradouro());
        bean.getEnderecoPessoaFisica().setBairro(json.getEnderBairro());
        bean.getEnderecoPessoaFisica().setCep(json.getEnderCep());

        if (json.getEnderTipoEndereco() != null) {
            bean.getEnderecoPessoaFisica().setTipoEndereco(
                    TipoEndereco.valueOf(json.getEnderTipoEndereco()));
        }

        if (json.getEnderCorrespondencia() != null) {
            bean.getEnderecoPessoaFisica().setCorrespondencia(
                    Correspondencia.valueOf(json.getEnderCorrespondencia()));
        }

        bean.getEnderecoPessoaFisica().setTelefonePrincipal(
                json.getEnderTelefonePrincipal());
        bean.getEnderecoPessoaFisica().setTelefoneSecundario(
                json.getEnderTelefoneSecundario());
        bean.getEnderecoPessoaFisica().setTelefoneOutro(
                json.getEnderTelefoneOutro());
        bean.getEnderecoPessoaFisica().setFax(json.getEnderFax());
        bean.getEnderecoPessoaFisica().setEncaminCorrespondencia(
                json.getEnderEncaminCorrespondencia());

        bean.setOutrasInforEmails(new OutrasInforEmails());
        bean.getOutrasInforEmails().setOutrasInformacoes(
                json.getOutrasInformacoes());
        bean.getOutrasInforEmails().setEmail(json.getEmail());
        bean.getOutrasInforEmails().setEmailOutros(json.getEmailOutros());

        bean.setInformacaoPartidaria(new InformacaoPartidaria());

        if (json.getPartidoId() != null) {
            bean.getInformacaoPartidaria().setPartido(
                    new Partido(json.getPartidoId()));
        }

        bean.getInformacaoPartidaria()
                .setTituloEleitor(json.getTituloEleitor());

        bean.getInformacaoPartidaria().setSessao(json.getSessao());
        bean.getInformacaoPartidaria().setZona(json.getZona());
        bean.setFacebook(json.getFacebook());
        bean.setTwitter(json.getTwitter());

        bean.setGrupos(PessoaFisicaGrupoFactory.getInstance().createJsonToBean(
                json.getGrupos()));

        bean.setYoutube(json.getYoutube());

        return bean;
    }

    public PessoaFisicaMiniJson createMini(PessoaFisica pessoaFisica) {

        if (pessoaFisica == null) {
            return null;
        }

        PessoaFisicaMiniJson mini = new PessoaFisicaMiniJson();
        mini.setId(pessoaFisica.getId());
        mini.setCodigo(pessoaFisica.getCodigo());
        mini.setNome(pessoaFisica.getDadosPessoais().getNome());

        return mini;
    }

    public PessoaFisica createMini(PessoaFisicaMiniJson json) {

        if (json == null) {
            return null;
        }

        PessoaFisica bean = new PessoaFisica();

        bean.setId(json.getId());

        return bean;
    }

}
