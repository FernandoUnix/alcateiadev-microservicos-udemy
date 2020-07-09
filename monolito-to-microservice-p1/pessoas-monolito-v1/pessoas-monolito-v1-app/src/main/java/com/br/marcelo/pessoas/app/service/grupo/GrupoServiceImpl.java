package com.br.marcelo.pessoas.app.service.grupo;

import com.br.marcelo.pessoas.app.entity.grupo.Grupo;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.repository.grupo.GrupoInputSelectSpecification;
import com.br.marcelo.pessoas.app.repository.grupo.GrupoRepository;
import com.br.marcelo.pessoas.app.repository.grupo.GrupoSpecification;
import com.br.marcelo.pessoas.app.service.pessoa.fisica.PessoaFisicaGrupoNomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PessoaFisicaGrupoNomesService pessoaFisicaGrupoNomesService;


    @Override
    public Grupo getById(Long id) {
        Grupo grupo = new Grupo();
        grupo.setId(id);

        Specification<Grupo> spec = new GrupoSpecification(grupo);

        return grupoRepository.findOne(spec);
    }

    @Override
    public Page<Grupo> consulta(Grupo grupo, int pos, String ordenacao) throws Exception {
        Specification<Grupo> spec = new GrupoSpecification(grupo.getNome(), ordenacao);
        return grupoRepository.findAll(spec, new PageRequest(pos,
                PagResultJson.NRO_TOTAL_REGISTROS));
    }

    @Transactional
    @Override
    public void incluir(Grupo grupo) throws Exception {

        if (isCodigoExiste(grupo.getCodigo())) {
            throw new Exception("Código já existe.");
        }

        grupoRepository.save(grupo);
    }

    @Transactional
    @Override
    public void alterar(Grupo grupo) throws Exception {

        if (isCodigoExiste(grupo.getCodigo(), grupo.getId())) {
            throw new Exception("Código já existe.");
        }

        Grupo grupoBanco = grupoRepository.findOne(grupo.getId());

        boolean alterNome = grupo.getNome().equals(grupoBanco.getNome());

        grupoBanco.setCodigo(grupo.getCodigo());
        grupoBanco.setNome(grupo.getNome());
        grupoBanco.setComentario(grupo.getComentario());

        grupoRepository.save(grupoBanco);

        if (alterNome) {
            pessoaFisicaGrupoNomesService.atualizarNomesGruposAllPF(grupoBanco.getId());
        }

    }

    @Transactional
    @Override
    public void excluir(Long id) throws Exception {

        Grupo bean = new Grupo();
        bean.setId(id);

        Specification<Grupo> spec = new GrupoSpecification(bean);

        bean = grupoRepository.findOne(spec);

        grupoRepository.delete(bean);

    }

    private boolean isCodigoExiste(String codigo) {
        return grupoRepository.getCodigo(codigo) > 0;
    }

    private boolean isCodigoExiste(String codigo, Long id) {
        return grupoRepository.getCodigo(codigo, id) > 0;
    }

    @Override
    public Page<Grupo> consultaComponente(String codigo, String nome,
                                          Long ignorar, Integer pos) {

        Grupo bean = new Grupo();
        bean.setCodigo(codigo);
        bean.setNome(nome);

        GrupoInputSelectSpecification spec = new GrupoInputSelectSpecification(bean, ignorar);

        return grupoRepository.findAll(spec, new PageRequest(pos, PagResultJson.NRO_TOTAL_REGISTROS));
    }
}








