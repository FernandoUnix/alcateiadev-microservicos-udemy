package com.br.marcelo.pessoas.app.repository.pessoa.fisica;

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

import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisicaGrupo;

public class PessoaFisicaGrupoSpecification extends AbstractSpecification
		implements Specification<PessoaFisicaGrupo> {

	private final PessoaFisica bean;

	private static final Map<String, String> MAPA_ORDENACAO = new HashMap<String, String>();

	private String ordenacao;
	
	static {
		MAPA_ORDENACAO.put("CODIGO", "id");
		MAPA_ORDENACAO.put("NOME", "dadosPessoais.nome");
	}
	
	public PessoaFisicaGrupoSpecification(PessoaFisica bean) {
		this.bean = bean;
	}
	
	public PessoaFisicaGrupoSpecification(PessoaFisica bean, String ordenacao) {
		this.bean = bean;
		this.ordenacao = ordenacao;
	}

	@Override
	public Predicate toPredicate(Root<PessoaFisicaGrupo> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<>();

		super.toPredicate(predicates, root, query, cb);

		if (bean != null ){
			predicates.add(cb.and(cb.equal(
					root.get("pessoaFisica").get("id"), bean.getId())));
		}

		query.orderBy(criarOrdenacao(ordenacao, cb, root, cb.asc(root.get("id")), MAPA_ORDENACAO));

		return andTogether(predicates, cb);
	}

}
