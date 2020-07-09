package com.br.marcelo.pessoas.app.repository.grupo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.br.marcelo.pessoas.app.repository.AbstractSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.br.marcelo.pessoas.app.entity.grupo.Grupo;

public class GrupoSpecification extends AbstractSpecification implements
		Specification<Grupo> {

	private final Grupo grupo;

	private static final Map<String, String> MAPA_ORDENACAO = new HashMap<String, String>();

	private String ordenacao;
	private String campo;

	static {
		MAPA_ORDENACAO.put("CODIGO", "codigo");
		MAPA_ORDENACAO.put("NOME", "nome");
	}
	
	public GrupoSpecification(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public GrupoSpecification(Grupo grupo, String ordenacao) {
		this.grupo = grupo;
		this.ordenacao = ordenacao;
	}

	public GrupoSpecification(String campo, String ordenacao) {
		this.grupo = new Grupo();
		this.ordenacao = ordenacao;
		this.campo = campo;
	}

	@Override
	public Predicate toPredicate(Root<Grupo> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<>();

		super.toPredicate(predicates, root, query, cb);

		if (grupo.getId() != null) {
			predicates.add(cb.and(cb.equal(root.get("id"), grupo.getId())));
		}

		if (!StringUtils.isEmpty(grupo.getCodigo())) {
			predicates.add(cb.and(cb.like(root.get("codigo"),
					"%" + grupo.getCodigo() + "%")));
		}

		if (!StringUtils.isEmpty(grupo.getNome())) {
			predicates.add(cb.and(cb.like(root.get("nome"),
					"%" + grupo.getNome() + "%")));
		}

		criarWhere(ordenacao, MAPA_ORDENACAO, root, cb, predicates, campo);

		query.orderBy(criarOrdenacao(ordenacao, cb, root, cb.asc(root.get("nome")), MAPA_ORDENACAO));

		return andTogether(predicates, cb);
	}

}
