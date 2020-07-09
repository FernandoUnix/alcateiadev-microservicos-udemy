package com.br.marcelo.pessoas.app.service.cargo;

import com.br.marcelo.pessoas.app.entity.cargo.Cargo;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.repository.cargo.CargoInputSelectSpecification;
import com.br.marcelo.pessoas.app.repository.cargo.CargoRepository;
import com.br.marcelo.pessoas.app.repository.cargo.CargoSpecification;
import com.br.marcelo.pessoas.app.exception.OpsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public Cargo getById(Long id) {
        Cargo bean = new Cargo();
        bean.setId(id);

        Specification<Cargo> spec = new CargoSpecification(bean);
        bean = cargoRepository.findOne(spec);

        if (bean.getCargoPai() != null) {
            bean.setCargoPai(getById(bean.getCargoPai().getId()));
        }

        return bean;
    }

    @Override
    public Page<Cargo> consulta(Cargo bean, int pos, String ordenacao) throws Exception {
        Specification<Cargo> spec = new CargoSpecification(bean.getNome(), ordenacao);
        return cargoRepository.findAll(spec, new PageRequest(pos, PagResultJson.NRO_TOTAL_REGISTROS));
    }

    @Override
    public Page<Cargo> consultaComponente(String codigo, String nome, Long ignorar, Integer pos) {

        Cargo bean = new Cargo();
        bean.setCodigo(codigo);
        bean.setNome(nome);

        CargoInputSelectSpecification spec = new CargoInputSelectSpecification(bean, ignorar);

        return cargoRepository.findAll(spec, new PageRequest(pos,
                PagResultJson.NRO_TOTAL_REGISTROS));
    }

    @Transactional
    @Override
    public void incluir(Cargo bean) throws Exception {

        if (isCodigoExiste(bean.getCodigo())) {
            throw new OpsException("Código já existe.");
        }

        validar(bean);

        bean.setCategoria(categoriaService.getById(bean.getCategoria().getId()));

        if (bean.getCargoPai() != null) {
            bean.setCargoPai(getById(bean.getCargoPai().getId()));
        }

        cargoRepository.save(bean);
    }

    private void validar(Cargo bean) throws Exception {

        if (bean.getCategoria() == null || bean.getCategoria().getId() == null) {
            throw new OpsException("Categoria é obrigatória.");
        }

    }

    @Transactional
    @Override
    public void alterar(Cargo bean) throws Exception {

        if (isCodigoExiste(bean.getCodigo(), bean.getId())) {
            throw new OpsException("Código já existe.");
        }

        validar(bean);

        Cargo beanBanco = cargoRepository.findOne(bean.getId());

        beanBanco.setCodigo(bean.getCodigo());
        beanBanco.setNome(bean.getNome());
        beanBanco.setCategoria(categoriaService.getById(bean.getCategoria().getId()));

        if (bean.getCargoPai() != null && bean.getCargoPai().getId() != null) {
            beanBanco.setCargoPai(getById(bean.getCargoPai().getId()));

            if (beanBanco.getId() == beanBanco.getCargoPai().getId()) {
                throw new Exception("Cargo pai não pode ser ele mesmo.");
            }
        } else {
            beanBanco.setCargoPai(null);
        }

        cargoRepository.save(beanBanco);
    }

    @Transactional
    @Override
    public void excluir(Long id) throws Exception {

        Cargo cargo = new Cargo();
        cargo.setId(id);

        Specification<Cargo> spec = new CargoSpecification(cargo);

        cargo = cargoRepository.findOne(spec);
        cargoRepository.delete(cargo);

    }

    private boolean isCodigoExiste(String codigo) {
        return cargoRepository.getCodigo(codigo) > 0;
    }

    private boolean isCodigoExiste(String codigo, Long id) {
        return cargoRepository.getCodigo(codigo, id) > 0;
    }


}
