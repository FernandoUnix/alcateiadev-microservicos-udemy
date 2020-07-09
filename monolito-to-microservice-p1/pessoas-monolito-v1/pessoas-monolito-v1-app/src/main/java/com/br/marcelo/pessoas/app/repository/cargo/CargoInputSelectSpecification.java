package com.br.marcelo.pessoas.app.repository.cargo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.br.marcelo.pessoas.app.entity.cargo.Cargo;
import com.br.marcelo.pessoas.app.repository.AbstractSpecification;

public class CargoInputSelectSpecification extends AbstractSpecification  implements Specification<Cargo> {

	private final Cargo bean;
	private Long ignorar;

	public CargoInputSelectSpecification(Cargo bean, Long ignorar) {
		this.bean = bean;
		this.ignorar = ignorar;
	}
	
	@Override
	public Predicate toPredicate(Root<Cargo> root, CriteriaQuery<?> query,	CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		super.toPredicate(predicates, root, query, cb);
		
		if (!StringUtils.isEmpty(bean.getNome())) {
			predicates.add(cb.and(cb.like(root.get("nome"),
					"%" + bean.getNome() + "%")));
		}

		if (!StringUtils.isEmpty(bean.getCodigo())) {
			predicates
					.add(cb.and(cb.equal(root.get("codigo"), bean.getCodigo())));
		}

		if( ignorar != null ){
			predicates.add(cb.and(cb.notEqual(root.get("id"), ignorar)));
		}
		
		query.orderBy(cb.asc(root.get("nome")));
		
		return andTogether(predicates, cb);
	}
	
	
	
}
