package com.br.marcelo.pessoas.app.controller.partido;

import com.br.marcelo.pessoas.app.entity.partido.Partido;
import com.br.marcelo.pessoas.app.exception.DeleteRuntimeException;
import com.br.marcelo.pessoas.app.factory.paginacao.PagResultFactory;
import com.br.marcelo.pessoas.app.factory.partido.PartidoFactory;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.json.partido.PartidoJson;
import com.br.marcelo.pessoas.app.service.partido.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PartidoJson getById(@Valid @NotNull @PathVariable("id") Long id) {
        Partido bean = partidoService.getById(id);
        return PartidoFactory.getInstance().create(bean);
    }

    @RequestMapping(value = "/consulta/componente", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consultaComponente(
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "ignorar", required = false) Long ignorar,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos) throws Exception {

        Page<Partido> pagList = partidoService.consultaComponente(codigo, nome, ignorar, Integer.parseInt(pos));
        List<Partido> list = pagList.getContent();
        List<PartidoJson> nList = PartidoFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/consulta", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consulta(
            @RequestParam(value = "sigla", required = false) String sigla,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos,
            @RequestParam(value = "ordenacao", required = false) String ordenacao)
            throws Exception {

        Partido bean = new Partido();
        bean.setSigla(sigla);

        Page<Partido> pagList = partidoService.consulta(bean,
                Integer.parseInt(pos), ordenacao);

        List<Partido> list = pagList.getContent();
        List<PartidoJson> nList = PartidoFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/incluir", method = RequestMethod.POST)
    public void incluir(@Valid @NotNull @RequestBody PartidoJson json)
            throws Exception {
        Partido bean = PartidoFactory.getInstance().create(json);
        partidoService.incluir(bean);
    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public void alterar(@Valid @NotNull @RequestBody PartidoJson json)
            throws Exception {

        Partido bean = PartidoFactory.getInstance().create(json);
        partidoService.alterar(bean);
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public void excluir(@Valid @NotNull @PathVariable("id") Long id)
            throws Exception {

        try {
            partidoService.excluir(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DeleteRuntimeException();
        }
    }

}
