package com.br.marcelo.pessoas.app.service.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisicaGrupo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PessoaFisicaService {

    void incluir(PessoaFisica bean, List<PessoaFisicaGrupo> grupo) throws Exception;

    void alterar(PessoaFisica bean, List<PessoaFisicaGrupo> grupos) throws Exception;

    Page<PessoaFisica> consulta(PessoaFisica bean, String ordenacao, int pos) throws Exception;

    List<String> getNomeExistente(Long id, String nome, Long cidade);

    PessoaFisica getById(Long id);

    Page<PessoaFisica> consultaComponente(Long codigo, String nome, Long ignorar, Integer pos);

    void excluir(Long id) throws Exception;

    Long gerarProximoCodigo();

}