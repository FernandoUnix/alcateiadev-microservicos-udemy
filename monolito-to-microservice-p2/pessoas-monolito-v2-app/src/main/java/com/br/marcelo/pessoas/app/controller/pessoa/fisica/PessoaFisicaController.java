package com.br.marcelo.pessoas.app.controller.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.pessoa.DadosPessoais;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import com.br.marcelo.pessoas.app.factory.paginacao.PagResultFactory;
import com.br.marcelo.pessoas.app.factory.pessoafisica.PessoaFisicaFactory;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.json.pessoafisica.PessoaFisicaJson;
import com.br.marcelo.pessoas.app.service.pessoa.fisica.PessoaFisicaService;
import com.br.marcelo.pessoas.app.exception.DeleteRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/pessoa/fisica/")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @RequestMapping(value = "/consulta/nome", method = RequestMethod.GET)
    @ResponseBody
    public List<String> nome(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "nome", required = true) String nome,
                             @RequestParam(value = "cidade", required = true) Long cidade
    ) throws Exception {
        return pessoaFisicaService.getNomeExistente(id, nome, cidade);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PessoaFisicaJson getById(@Valid @NotNull @PathVariable("id") Long id) {
        PessoaFisica bean = pessoaFisicaService.getById(id);
        return PessoaFisicaFactory.getInstance().create(bean);
    }

    @RequestMapping(value = "/consulta/proximoCodigo", method = RequestMethod.GET)
    @ResponseBody
    public String proximoCodigo() throws Exception {
        return pessoaFisicaService.gerarProximoCodigo().toString();
    }

    @RequestMapping(value = "/consulta/componente", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consultaComponente(
            @RequestParam(value = "codigo", required = false) Long codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "ignorar", required = false) Long ignorar,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos) throws Exception {

        Page<PessoaFisica> pagList = pessoaFisicaService.consultaComponente(codigo, nome, ignorar, Integer.parseInt(pos));
        List<PessoaFisica> list = pagList.getContent();
        List<PessoaFisicaJson> nList = PessoaFisicaFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/consulta", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consulta(
            @RequestParam(value = "codigo", required = false) Long codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos,
            @RequestParam(value = "ordenacao", required = false) String ordenacao)
            throws Exception {

        PessoaFisica bean = new PessoaFisica();
        bean.setCodigo(codigo);
        bean.setDadosPessoais(new DadosPessoais(nome));

        Page<PessoaFisica> pagList = pessoaFisicaService.consulta(bean, ordenacao,
                Integer.parseInt(pos));

        List<PessoaFisica> list = pagList.getContent();
        List<PessoaFisicaJson> nList = PessoaFisicaFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/incluir", method = RequestMethod.POST)
    public void incluir(@Valid @NotNull @RequestBody PessoaFisicaJson json)
            throws Exception {
        PessoaFisica bean = PessoaFisicaFactory.getInstance().create(json);
        pessoaFisicaService.incluir(bean, bean.getGrupos());
    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public void alterar(@Valid @NotNull @RequestBody PessoaFisicaJson json)
            throws Exception {
        PessoaFisica bean = PessoaFisicaFactory.getInstance().create(json);
        pessoaFisicaService.alterar(bean, bean.getGrupos());
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public void excluir(@Valid @NotNull @PathVariable("id") Long id)
            throws Exception {
        try {
            pessoaFisicaService.excluir(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DeleteRuntimeException();
        }
    }

}
