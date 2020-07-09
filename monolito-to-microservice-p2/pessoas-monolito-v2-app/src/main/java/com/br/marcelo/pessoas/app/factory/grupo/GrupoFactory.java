package com.br.marcelo.pessoas.app.factory.grupo;

import com.br.marcelo.pessoas.app.entity.grupo.Grupo;
import com.br.marcelo.pessoas.app.json.grupo.GrupoJson;

import java.util.ArrayList;
import java.util.List;


public class GrupoFactory {

    private static final GrupoFactory INSTANCE = new GrupoFactory();

    private GrupoFactory() {
    }

    public static GrupoFactory getInstance() {
        return INSTANCE;
    }

    public Grupo create(GrupoJson json) {

        Grupo grupo = new Grupo();

        if (json == null) {
            return grupo;
        }

        grupo.setId(json.getId());
        grupo.setNome(json.getNome());
        grupo.setCodigo(json.getCodigo());
        grupo.setComentario(json.getComentario());

        return grupo;
    }

    public List<GrupoJson> create(List<Grupo> list) {

        List<GrupoJson> nList = new ArrayList<GrupoJson>();
        for (Grupo item : list) {
            nList.add(create(item));
        }

        return nList;
    }

    public GrupoJson create(Grupo item) {

        GrupoJson json = new GrupoJson();
        json.setCodigo(item.getCodigo());
        json.setNome(item.getNome());
        json.setComentario(item.getComentario());
        json.setId(item.getId());

        return json;
    }

}
