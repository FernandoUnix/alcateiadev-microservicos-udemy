package com.br.marcelo.pessoas.app.controller.cargo;

import com.br.marcelo.pessoas.app.entity.cargo.Cargo;
import com.br.marcelo.pessoas.app.factory.cargo.CargoFactory;
import com.br.marcelo.pessoas.app.factory.paginacao.PagResultFactory;
import com.br.marcelo.pessoas.app.json.cargo.CargoJson;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.service.cargo.CargoService;
import com.br.marcelo.pessoas.app.exception.DeleteRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CargoJson getById(@Valid @NotNull @PathVariable("id") Long id) {
        Cargo bean = cargoService.getById(id);
        return CargoFactory.getInstance().create(bean);
    }

    @RequestMapping(value = "/consulta/componente", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consultaComponente(
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "ignorar", required = false) Long ignorar,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos
    ) throws Exception {

        Page<Cargo> pagList = cargoService.consultaComponente(codigo, nome, ignorar, Integer.parseInt(pos));
        List<Cargo> list = pagList.getContent();
        List<CargoJson> nList = CargoFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/consulta", method = RequestMethod.GET)
    @ResponseBody
    public PagResultJson consulta(
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "pos", required = false, defaultValue = "0") String pos,
            @RequestParam(value = "ordenacao", required = false) String ordenacao) throws Exception {

        Cargo bean = new Cargo();
        bean.setCodigo(codigo);
        bean.setNome(nome);

        Page<Cargo> pagList = cargoService.consulta(bean, Integer.parseInt(pos), ordenacao);

        List<Cargo> list = pagList.getContent();
        List<CargoJson> nList = CargoFactory.getInstance().create(list);

        return PagResultFactory.getInstance().create(pagList, nList);
    }

    @RequestMapping(value = "/incluir", method = RequestMethod.POST)
    public void incluir(@Valid @NotNull @RequestBody CargoJson json) throws Exception {
        Cargo bean = CargoFactory.getInstance().create(json);
        cargoService.incluir(bean);
    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public void alterar(@Valid @NotNull @RequestBody CargoJson json) throws Exception {
        Cargo bean = CargoFactory.getInstance().create(json);
        cargoService.alterar(bean);
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public void excluir(@Valid @NotNull @PathVariable("id") Long id) throws Exception {

        try {
            cargoService.excluir(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DeleteRuntimeException();
        }
    }


}
