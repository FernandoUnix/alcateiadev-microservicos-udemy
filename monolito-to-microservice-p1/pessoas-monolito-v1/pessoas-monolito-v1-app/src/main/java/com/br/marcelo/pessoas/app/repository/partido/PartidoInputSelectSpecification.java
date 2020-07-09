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
import java.util.List;

public class PartidoInputSelectSpecification extends AbstractSpecification implements Specification<Partido> {

    private final Partido partido;

    public PartidoInputSelectSpecification(Partido partido) {
        this.partido = partido;
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

        query.orderBy(cb.asc(root.get("sigla")));

        return andTogether(predicates, cb);
    }

}
