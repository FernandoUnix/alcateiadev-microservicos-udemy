package com.br.marcelo.pessoas.app.repository.pessoa.fisica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.br.marcelo.pessoas.app.repository.AbstractSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;

public class PessoaFisicaInputSelectSpecification extends AbstractSpecification implements Specification<PessoaFisica> {

	private final PessoaFisica bean;
	private Long ignorar;

	public PessoaFisicaInputSelectSpecification(PessoaFisica bean, Long ignorar) {
		this.bean = bean;
		this.ignorar = ignorar;
	}

	@Override
	public Predicate toPredicate(Root<PessoaFisica> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<>();
		
		super.toPredicate(predicates, root, query, cb);
		
		if (!StringUtils.isEmpty(bean.getCodigo())) {
			predicates
					.add(cb.and(cb.equal(root.get("codigo"), bean.getCodigo())));
		}

		if( ignorar != null ){
			predicates.add(cb.and(cb.notEqual(root.get("id"), ignorar)));
		}
		
		if (bean.getDadosPessoais() != null
				&& bean.getDadosPessoais().getNome() != null) {
			predicates.add(cb.and(cb.like(
					root.get("dadosPessoais").get("nome"), "%"
							+ bean.getDadosPessoais().getNome()
							+ "%")));
		}
		
		query.orderBy(cb.asc(root.get("dadosPessoais").get("nome")));
		
		return andTogether(predicates, cb);
	}

}
