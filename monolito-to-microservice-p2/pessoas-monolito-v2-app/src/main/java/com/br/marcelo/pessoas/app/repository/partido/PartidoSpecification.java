package com.br.marcelo.pessoas.app.repository.partido;

import com.br.marcelo.pessoas.app.repository.AbstractSpecification;
import com.br.marcelo.pessoas.app.entity.partido.Partido;
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

public class PartidoSpecification extends AbstractSpecification implements Specification<Partido> {

    private final Partido partido;

    private static final Map<String, String> MAPA_ORDENACAO = new HashMap<String, String>();

    private String ordenacao;

    private String campo;

    static {
        MAPA_ORDENACAO.put("SIGLA", "sigla");
        MAPA_ORDENACAO.put("DESCRICAO", "descricao");
    }

    public PartidoSpecification(Partido partido) {
        this.partido = partido;
    }

    public PartidoSpecification(Partido partido, String ordenacao) {
        this.partido = partido;
        this.ordenacao = ordenacao;
    }

    public PartidoSpecification(String campo, String ordenacao) {
        this.partido = new Partido();
        this.ordenacao = ordenacao;
        this.campo = campo;
    }

    @Override
    public Predicate toPredicate(Root<Partido> root, CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        super.toPredicate(predicates, root, query, cb);

        if (partido.getId() != null) {
            predicates.add(cb.and(cb.equal(root.get("id"), partido.getId())));
        }

        if (!StringUtils.isEmpty(partido.getSigla())) {
            predicates.add(cb.and(cb.like(root.get("sigla"), "%" + partido.getSigla() + "%")));
        }

        if (!StringUtils.isEmpty(partido.getDescricao())) {
            predicates.add(cb.and(cb.like(root.get("descricao"), "%" + partido.getDescricao() + "%")));
        }

        criarWhere(ordenacao, MAPA_ORDENACAO, root, cb, predicates, campo);

        query.orderBy(criarOrdenacao(ordenacao, cb, root, cb.asc(root.get("sigla")), MAPA_ORDENACAO));

        return andTogether(predicates, cb);
    }

}
