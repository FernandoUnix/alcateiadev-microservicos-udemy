package com.br.marcelo.pessoas.app.controller.grupo;

import com.br.marcelo.pessoas.app.entity.grupo.Grupo;
import com.br.marcelo.pessoas.app.factory.grupo.GrupoFactory;
import com.br.marcelo.pessoas.app.factory.paginacao.PagResultFactory;
import com.br.marcelo.pessoas.app.json.grupo.GrupoJson;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.service.grupo.GrupoService;
import com.br.marcelo.pessoas.app.exception.DeleteRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GrupoJson getById(@Valid @NotNull @PathVariable("id") Long id) {
        Grupo grupo = grupoService.getById(id);
        return GrupoFactory.getInstance().create(grupo);
    }

    @RequestMapping(value = "/consulta/componente", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consultaComponente(
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "ignorar", required = false) Long ignorar,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos) throws Exception {

        Page<Grupo> pagList = grupoService.consultaComponente(codigo, nome, ignorar, Integer.parseInt(pos));
        List<Grupo> list = pagList.getContent();
        List<GrupoJson> nList = GrupoFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/consulta", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consulta(
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos,
            @RequestParam(value = "ordenacao", required = false) String ordenacao) throws Exception {

        Grupo grupo = new Grupo();
        grupo.setCodigo(codigo);
        grupo.setNome(nome);

        Page<Grupo> pagList = grupoService.consulta(grupo, Integer.parseInt(pos), ordenacao);

        List<Grupo> list = pagList.getContent();
        List<GrupoJson> nList = GrupoFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/incluir", method = RequestMethod.POST)
    public void incluir(@Valid @NotNull @RequestBody GrupoJson json) throws Exception {

        Grupo grupo = GrupoFactory.getInstance().create(json);
        grupoService.incluir(grupo);
    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public void alterar(@Valid @NotNull @RequestBody GrupoJson json) throws Exception {

        Grupo grupo = GrupoFactory.getInstance().create(json);
        grupoService.alterar(grupo);
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public void excluir(@Valid @NotNull @PathVariable("id") Long id) throws Exception {

        try {
            grupoService.excluir(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DeleteRuntimeException();
        }
    }


}
