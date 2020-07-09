package com.br.marcelo.pessoas.app.repository.cargo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.br.marcelo.pessoas.app.entity.cargo.Cargo;
import com.br.marcelo.pessoas.app.repository.AbstractSpecification;

public class CargoSpecification extends AbstractSpecification  implements Specification<Cargo> {

	private final Cargo bean;

	private static final Map<String, String> MAPA_ORDENACAO = new HashMap<String, String>();

	private String ordenacao;
	private String campo;

	static {
		MAPA_ORDENACAO.put("CODIGO", "id");
		MAPA_ORDENACAO.put("NOME", "nome");
		MAPA_ORDENACAO.put("CATEGORIA", "categoria.nome");
	}

	public CargoSpecification(Cargo bean) {
		this.bean = bean;
	}
	
	public CargoSpecification(Cargo bean, String ordenacao) {
		this.bean = bean;
		this.ordenacao = ordenacao;
	}

	public CargoSpecification(String campo, String ordenacao) {
		this.bean = new Cargo();
		this.ordenacao = ordenacao;
		this.campo = campo;
	}
	
	@Override
	public Predicate toPredicate(Root<Cargo> root, CriteriaQuery<?> query,	CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		super.toPredicate(predicates, root, query, cb);
		
		if( bean.getId() != null){
			predicates.add(cb.and(cb.equal(root.get("id"), bean.getId())));
		}
		
		if( !StringUtils.isEmpty(bean.getNome()) ){
			predicates.add(cb.and(cb.like(root.get("nome"), "%" + bean.getNome() +"%")));
		}
		
		if( bean.getCategoria() != null && bean.getCategoria().getId() != null ){
			predicates.add(cb.and(cb.equal(root.get("categoria").get("id"), bean.getCategoria().getId())));
		}

		criarWhere(ordenacao, MAPA_ORDENACAO, root, cb, predicates, campo);

		query.orderBy(criarOrdenacao(ordenacao, cb, root, cb.asc(root.get("nome")), MAPA_ORDENACAO));
		
		return andTogether(predicates, cb);
	}
	
	
	
}
