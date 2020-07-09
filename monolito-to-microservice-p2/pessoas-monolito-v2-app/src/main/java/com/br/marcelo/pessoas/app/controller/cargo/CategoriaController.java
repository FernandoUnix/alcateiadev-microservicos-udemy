package com.br.marcelo.pessoas.app.controller.cargo;

import com.br.marcelo.pessoas.app.entity.cargo.Categoria;
import com.br.marcelo.pessoas.app.factory.cargo.CategoriaFactory;
import com.br.marcelo.pessoas.app.factory.paginacao.PagResultFactory;
import com.br.marcelo.pessoas.app.json.cargo.CategoriaJson;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.service.cargo.CategoriaService;
import com.br.marcelo.pessoas.app.exception.DeleteRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/cargo/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CategoriaJson getById(@Valid @NotNull @PathVariable("id") Long id) {
        Categoria categoria = categoriaService.getById(id);
        return CategoriaFactory.getInstance().create(categoria);
    }

    @RequestMapping(value = "/consulta/componente", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoriaJson> consultaComponente(@RequestParam(value = "nome", required = false) String nome) throws Exception {

        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        List<Categoria> list = categoriaService.consultaComponente(categoria);
        List<CategoriaJson> nList = CategoriaFactory.getInstance().create(list);

        return nList;
    }

    @RequestMapping(value = "/consulta", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consulta(
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos,
            @RequestParam(value = "ordenacao", required = false) String ordenacao) throws Exception {

        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        Page<Categoria> pagList = categoriaService.consulta(categoria, Integer.parseInt(pos), ordenacao);

        List<Categoria> list = pagList.getContent();
        List<CategoriaJson> nList = CategoriaFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/incluir", method = RequestMethod.POST)
    public void incluir(@Valid @NotNull @RequestBody CategoriaJson json) throws Exception {

        Categoria categoria = CategoriaFactory.getInstance().create(json);
        categoriaService.incluir(categoria);
    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public void alterar(@Valid @NotNull @RequestBody CategoriaJson json) throws Exception {

        Categoria categoria = CategoriaFactory.getInstance().create(json);
        categoriaService.alterar(categoria);
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public void excluir(@Valid @NotNull @PathVariable("id") Long id) throws Exception {
        try {
            categoriaService.excluir(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DeleteRuntimeException();
        }
    }


}
