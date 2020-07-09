package com.br.marcelo.pessoas.app.service.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.pessoa.DadosPessoais;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisicaGrupo;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.repository.pessoa.fisica.PessoaFisicaInputSelectSpecification;
import com.br.marcelo.pessoas.app.repository.pessoa.fisica.PessoaFisicaRepository;
import com.br.marcelo.pessoas.app.repository.pessoa.fisica.PessoaFisicaSpecification;
import com.br.marcelo.pessoas.app.service.grupo.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

    private static final long VALOR_INICIAL_CODIGO = 1L;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PessoaFisicaGrupoService pessoaFisicaGrupoService;

    @Autowired
    private PessoaFisicaGrupoNomesService pessoaFisicaGrupoNomesService;

    @Override
    public List<String> getNomeExistente(Long id, String nome, Long cidade) {

        if (id == null) {
            return tratarRetornoNome(pessoaFisicaRepository.getNome(nome.trim().toUpperCase()));
        }

        return tratarRetornoNome(pessoaFisicaRepository.getNome(id, nome.trim().toUpperCase()));
    }

    private List<String> tratarRetornoNome(List<Object[]> nomes) {

        if (nomes == null) {
            return null;
        }

        List<String> lista = new ArrayList<>();

        for (Object[] item : nomes) {
            StringBuilder sb = new StringBuilder();
            sb.append("Código: '").append(item[0]).append("'").append(", Nome: '").append(item[1]).append("' ");
            lista.add(sb.toString());
        }

        return lista;
    }

    @Override
    public PessoaFisica getById(Long id) {
        PessoaFisica bean = new PessoaFisica();
        bean.setId(id);

        Specification<PessoaFisica> spec = new PessoaFisicaSpecification(bean);
        bean = pessoaFisicaRepository.findOne(spec);

        bean.setGrupos(pessoaFisicaGrupoService.getGrupos(bean));

        return bean;
    }

    @Override
    public Page<PessoaFisica> consulta(PessoaFisica bean, String ordenacao, int pos)
            throws Exception {

        Specification<PessoaFisica> spec = new PessoaFisicaSpecification(bean.getDadosPessoais().getNome(), ordenacao);

        return pessoaFisicaRepository.findAll(spec, new PageRequest(pos,
                PagResultJson.NRO_TOTAL_REGISTROS));

    }

    @Override
    public Page<PessoaFisica> consultaComponente(Long codigo, String nome,
                                                 Long ignorar, Integer pos) {

        PessoaFisica bean = new PessoaFisica();
        bean.setCodigo(codigo);
        bean.setDadosPessoais(new DadosPessoais(nome));

        PessoaFisicaInputSelectSpecification spec = new PessoaFisicaInputSelectSpecification(
                bean, ignorar);

        return pessoaFisicaRepository.findAll(spec, new PageRequest(pos,
                PagResultJson.NRO_TOTAL_REGISTROS));

    }

    @Transactional
    @Override
    public void incluir(PessoaFisica bean, List<PessoaFisicaGrupo> grupos)
            throws Exception {

        validar(bean);

        bean.setCodigo(gerarProximoCodigo());

        pessoaFisicaRepository.save(bean);
        pessoaFisicaGrupoService.incluir(bean, grupos);
        pessoaFisicaGrupoNomesService.atualizarNomesGrupos(bean.getId());
    }

    @Transactional
    @Override
    public void alterar(PessoaFisica bean, List<PessoaFisicaGrupo> grupos)
            throws Exception {

        PessoaFisica beanBanco = pessoaFisicaRepository.findOne(bean.getId());

        validar(beanBanco);


        beanBanco.setDadosPessoais(bean.getDadosPessoais());
        beanBanco.setEnderecoPessoaFisica(bean.getEnderecoPessoaFisica());
        beanBanco.setOutrasInforEmails(bean.getOutrasInforEmails());
        beanBanco.setInformacaoPartidaria(bean.getInformacaoPartidaria());
        beanBanco.setFacebook(bean.getFacebook());
        beanBanco.setTwitter(bean.getTwitter());
        beanBanco.setYoutube(bean.getYoutube());

        pessoaFisicaRepository.save(beanBanco);
        pessoaFisicaGrupoService.alterar(beanBanco, grupos);
        pessoaFisicaGrupoNomesService.atualizarNomesGrupos(bean.getId());
    }

    @Override
    public Long gerarProximoCodigo() {
        Long valor = pessoaFisicaRepository.gerarProximoCodigo();

        if (valor == null) {
            valor = VALOR_INICIAL_CODIGO;
        } else {
            valor++;
        }

        return valor;
    }

    private void validar(PessoaFisica bean) throws Exception {

        if (bean == null) {
            throw new Exception("Pessoa física não encontrada.");
        }

    }

    @Transactional
    @Override
    public void excluir(Long id) throws Exception {

        PessoaFisica beanBanco = new PessoaFisica(id);

        Specification<PessoaFisica> spec = new PessoaFisicaSpecification(
                beanBanco);
        beanBanco = pessoaFisicaRepository.findOne(spec);

        pessoaFisicaGrupoService.excluir(beanBanco);
        pessoaFisicaRepository.delete(beanBanco);


    }

}
