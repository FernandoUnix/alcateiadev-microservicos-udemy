package com.br.marcelo.pessoas.app.repository.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisica;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long>, JpaSpecificationExecutor<PessoaFisica> {

    @Query("select max(obj.codigo) from PessoaFisica obj ")
    Long gerarProximoCodigo();

    @Modifying
    @Query(value = "update pessoa_fisica set nomes_grupos = ?2 where id = ?1", nativeQuery = true)
    void atualizarNomesGrupo(Long id, String nomes);

    @Query("select obj.codigo, obj.dadosPessoais.nome from PessoaFisica obj where  upper(obj.dadosPessoais.nome) = ?1 ")
    List<Object[]> getNome(String nome);

    @Query("select obj.codigo, obj.dadosPessoais.nome from PessoaFisica obj where  upper(obj.dadosPessoais.nome) = ?2 and obj.id <> ?1  ")
    List<Object[]> getNome(Long id, String nome);
}
