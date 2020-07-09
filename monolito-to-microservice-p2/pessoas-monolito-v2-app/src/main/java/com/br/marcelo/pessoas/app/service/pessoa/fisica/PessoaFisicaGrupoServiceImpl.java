package com.br.marcelo.pessoas.app.service.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisicaGrupo;
import com.br.marcelo.pessoas.app.repository.pessoa.fisica.PessoaFisicaGrupoRepository;
import com.br.marcelo.pessoas.app.repository.pessoa.fisica.PessoaFisicaGrupoSpecification;
import com.br.marcelo.pessoas.app.service.grupo.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PessoaFisicaGrupoServiceImpl implements PessoaFisicaGrupoService {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PessoaFisicaGrupoRepository pessoaFisicaGrupoRepository;

    @Override
    public List<PessoaFisicaGrupo> getGrupos(PessoaFisica bean) {
        return pessoaFisicaGrupoRepository.findAll(new PessoaFisicaGrupoSpecification(bean));
    }

    @Transactional
    @Override
    public void incluir(PessoaFisica bean, List<PessoaFisicaGrupo> grupos) {

        for (PessoaFisicaGrupo grupo : grupos) {
            grupo.setGrupo(grupoService.getById(grupo.getGrupo().getId()));
            grupo.setPessoaFisica(bean);

            pessoaFisicaGrupoRepository.save(grupo);
        }

    }

    @Transactional
    @Override
    public void alterar(PessoaFisica bean,
                        List<PessoaFisicaGrupo> grupos) {

        pessoaFisicaGrupoRepository.excluirTodosGrupos(bean.getId());
        incluir(bean, grupos);

    }

    @Transactional
    @Override
    public void excluir(PessoaFisica bean) {

        pessoaFisicaGrupoRepository.excluirTodosGrupos(bean.getId());

    }

}
