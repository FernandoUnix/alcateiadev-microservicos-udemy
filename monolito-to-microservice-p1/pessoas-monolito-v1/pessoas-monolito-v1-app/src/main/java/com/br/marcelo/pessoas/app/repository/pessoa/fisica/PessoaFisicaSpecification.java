package com.br.marcelo.pessoas.app.repository.pessoa.fisica;

import com.br.marcelo.pessoas.app.repository.AbstractSpecification;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PessoaFisicaSpecification extends AbstractSpecification
        implements Specification<PessoaFisica> {

    private static final Map<String, String> MAPA_ORDENACAO = new HashMap<String, String>();

    static {
        MAPA_ORDENACAO.put("CODIGO", "codigo");
        MAPA_ORDENACAO.put("NOME", "dadosPessoais.nome");
        MAPA_ORDENACAO.put("TELEFONE", "enderecoPessoaFisica.telefonePrincipal");
        MAPA_ORDENACAO.put("CIDADE", "enderecoPessoaFisica.cidade.nome");
        MAPA_ORDENACAO.put("BAIRRO", "enderecoPessoaFisica.bairro");


		/*MAPA_ORDENACAO.put("CIDADE", "enderecoPessoaFisica.cidade.cidade.nome");
		MAPA_ORDENACAO.put("UF", "enderecoPessoaFisica.cidade.cidade.estado.uf");
		*/
    }

    private final PessoaFisica bean;
    private String ordenacao;
    private String campo;

    public PessoaFisicaSpecification(PessoaFisica bean) {
        this.bean = bean;
    }

    public PessoaFisicaSpecification(PessoaFisica bean, String ordenacao) {
        this.bean = bean;
        this.ordenacao = ordenacao;
    }

    public PessoaFisicaSpecification(String campo, String ordenacao) {
        this.bean = new PessoaFisica();
        this.ordenacao = ordenacao;
        this.campo = campo;
    }

    @Override
    public Predicate toPredicate(Root<PessoaFisica> root,
                                 CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        super.toPredicate(predicates, root, query, cb);

        if (bean.getId() != null) {
            predicates.add(cb.and(cb.equal(root.get("id"), bean.getId())));
        }

        if (!StringUtils.isEmpty(bean.getCodigo())) {
            predicates
                    .add(cb.or(cb.equal(root.get("codigo"), bean.getCodigo())));
        }

        if (bean.getDadosPessoais() != null
                && bean.getDadosPessoais().getNome() != null) {
            predicates.add(cb.or(cb.like(
                    root.get("dadosPessoais").get("nome"), "%"
                            + bean.getDadosPessoais().getNome()
                            + "%")));
        }

        if (bean.getEnderecoPessoaFisica() != null
                && bean.getEnderecoPessoaFisica().getTelefonePrincipal() != null) {
            predicates.add(cb.or(cb.equal(
                    root.get("enderecoPessoaFisica").get("telefonePrincipal"), bean.getEnderecoPessoaFisica().getTelefonePrincipal())));
        }

        if (bean.getEnderecoPessoaFisica() != null
                && bean.getEnderecoPessoaFisica().getBairro() != null) {
            predicates.add(cb.or(cb.equal(
                    root.get("enderecoPessoaFisica").get("bairro"), bean.getEnderecoPessoaFisica().getBairro())));
        }

        criarWhere(ordenacao, MAPA_ORDENACAO, root, cb, predicates, campo);

        query.orderBy(criarOrdenacao(ordenacao, cb, root, cb.asc(root.get("dadosPessoais").get("nome")), MAPA_ORDENACAO));

        return andTogether(predicates, cb);
    }

}


