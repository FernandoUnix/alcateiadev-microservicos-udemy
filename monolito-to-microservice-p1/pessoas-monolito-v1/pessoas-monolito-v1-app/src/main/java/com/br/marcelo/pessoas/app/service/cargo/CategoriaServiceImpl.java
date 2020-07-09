package com.br.marcelo.pessoas.app.service.cargo;

import com.br.marcelo.pessoas.app.entity.cargo.Categoria;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.repository.cargo.CategoriaRepository;
import com.br.marcelo.pessoas.app.repository.cargo.CategoriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria getById(Long id) {
        Categoria categoria = new Categoria();
        categoria.setId(id);

        Specification<Categoria> spec = new CategoriaSpecification(categoria);

        return categoriaRepository.findOne(spec);
    }

    @Override
    public List<Categoria> consultaComponente(Categoria categoria) throws Exception {
        Specification<Categoria> spec = new CategoriaSpecification(categoria);
        return categoriaRepository.findAll(spec);
    }

    @Override
    public Page<Categoria> consulta(Categoria categoria, int pos, String ordenacao) throws Exception {
        Specification<Categoria> spec = new CategoriaSpecification(categoria.getNome(), ordenacao);
        return categoriaRepository.findAll(spec, new PageRequest(pos, PagResultJson.NRO_TOTAL_REGISTROS));
    }

    @Transactional
    @Override
    public void incluir(Categoria categoria) throws Exception {
        categoriaRepository.save(categoria);
    }

    @Transactional
    @Override
    public void alterar(Categoria categoria) throws Exception {

        Categoria categoriaBanco = categoriaRepository.findOne(categoria.getId());
        categoriaBanco.setNome(categoria.getNome());

        categoriaRepository.save(categoriaBanco);
    }

    @Transactional
    @Override
    public void excluir(Long id) throws Exception {

        Categoria bean = new Categoria();
        bean.setId(id);

        Specification<Categoria> spec = new CategoriaSpecification(bean);

        bean = categoriaRepository.findOne(spec);
        categoriaRepository.delete(bean);

    }

}
