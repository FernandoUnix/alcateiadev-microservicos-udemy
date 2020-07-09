package com.br.marcelo.pessoas.app.repository;

import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

public class AbstractSpecification {

    public void toPredicate(List<Predicate> predicates, Root root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    }

    protected Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }

    @SuppressWarnings("rawtypes")
    protected Order[] criarOrdenacao(String ordenacao, CriteriaBuilder cb, Root root, Order orderPadrao, final Map<String, String> mapaCampos) {

        if (StringUtils.isEmpty(ordenacao)) {
            return new Order[]{orderPadrao};
        }

        String[] itens = ordenacao.split("_");

        Order[] orders = new Order[itens.length];

        int posOrder = 0;
        for (String item : itens) {

            if (StringUtils.isEmpty(item)) {
                continue;
            }

            String campo = mapaCampos.get(item);

            if (campo.indexOf(".") < 0) {
                orders[posOrder++] = cb.asc(root.get(campo));
            } else {

                String[] itensCampo = campo.split("\\.");
                Path path = root.get(itensCampo[0]);

                for (int loop = 1; loop < itensCampo.length; loop++) {
                    path = path.get(itensCampo[loop]);
                }

                orders[posOrder++] = cb.asc(path);
            }
        }

        return orders;
    }

    protected void criarWhere(String ordenacao, Map<String, String> mapaCampos, Root root,
                              CriteriaBuilder cb, List<Predicate> predicates, String campoString) {

        if (StringUtils.isEmpty(ordenacao) || StringUtils.isEmpty(campoString)) {
            return;
        }

        String[] itens = ordenacao.split("_");

        Order[] orders = new Order[itens.length];

        int posOrder = 0;
        for (String item : itens) {

            if (StringUtils.isEmpty(item)) {
                continue;
            }

            String campo = mapaCampos.get(item);

            if (campo.indexOf(".") < 0) {
                predicates.add(cb.or(cb.like(root.get(campo).as(String.class), "%" + campoString + "%")));
            } else {

                String[] itensCampo = campo.split("\\.");
                Path path = root.get(itensCampo[0]);

                for (int loop = 1; loop < itensCampo.length; loop++) {
                    path = path.get(itensCampo[loop]);
                }

                predicates.add(cb.or(cb.like(path.as(String.class), "%" + campoString + "%")));
            }
        }
    }
}
