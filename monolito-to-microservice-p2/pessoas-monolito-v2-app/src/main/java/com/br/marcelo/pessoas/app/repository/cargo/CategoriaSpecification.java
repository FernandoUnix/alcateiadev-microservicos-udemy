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

import com.br.marcelo.pessoas.app.entity.cargo.Categoria;
import com.br.marcelo.pessoas.app.repository.AbstractSpecification;

public class CategoriaSpecification extends AbstractSpecification implements Specification<Categoria> {

	private final Categoria categoria;

	private static final Map<String, String> MAPA_ORDENACAO = new HashMap<String, String>();

	private String ordenacao;
	private String campo;

	static {
		MAPA_ORDENACAO.put("CODIGO", "id");
		MAPA_ORDENACAO.put("NOME", "nome");
	}
	
	public CategoriaSpecification(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public CategoriaSpecification(Categoria categoria, String ordenacao) {
		this.categoria = categoria;
		this.ordenacao = ordenacao;
	}

	public CategoriaSpecification(String campo, String ordenacao) {
		this.categoria = new Categoria();
		this.ordenacao = ordenacao;
		this.campo = campo;
	}
	
	@Override
	public Predicate toPredicate(Root<Categoria> root, CriteriaQuery<?> query,	CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<>();

		super.toPredicate(predicates, root, query, cb);
		
		if( categoria.getId() != null){
			predicates.add(cb.and(cb.equal(root.get("id"), categoria.getId())));
		}
		
		if( !StringUtils.isEmpty(categoria.getNome()) ){
			predicates.add(cb.and(cb.like(root.get("nome"), "%" + categoria.getNome() +"%")));
		}

		criarWhere(ordenacao, MAPA_ORDENACAO, root, cb, predicates, campo);

		query.orderBy(criarOrdenacao(ordenacao, cb, root, cb.asc(root.get("nome")), MAPA_ORDENACAO));
		
		return andTogether(predicates, cb);
	}
	
}
