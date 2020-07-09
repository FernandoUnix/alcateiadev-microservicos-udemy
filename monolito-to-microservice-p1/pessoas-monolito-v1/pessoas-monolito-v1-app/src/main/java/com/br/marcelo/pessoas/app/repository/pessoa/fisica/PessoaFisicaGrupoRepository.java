package com.br.marcelo.pessoas.app.repository.pessoa.fisica;

import com.br.marcelo.pessoas.app.entity.pessoa.fisica.PessoaFisicaGrupo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PessoaFisicaGrupoRepository extends CrudRepository<PessoaFisicaGrupo, Long>, JpaSpecificationExecutor<PessoaFisicaGrupo> {

    @Query("from PessoaFisicaGrupo obj where obj.pessoaFisica.id = ?1 ")
    List<PessoaFisicaGrupo> buscarTodosGrupos(Long idPessoaFisica);

    @Modifying
    @Transactional
    @Query("delete from PessoaFisicaGrupo obj where obj.pessoaFisica.id = ?1  ")
    void excluirTodosGrupos(Long idPessoaFisica);

    @Query(value = "select gr.nome from pessoa_fisica_grupo obj inner join grupo gr on gr.id = obj.id_grupo where obj.id_pessoa_fisica = ?1", nativeQuery = true)
    List<String> findGruposNome(Long id);

    @Query(value = "select obj.id_pessoa_fisica from pessoa_fisica_grupo obj inner join grupo gr on gr.id = obj.id_grupo where obj.id_grupo = ?1", nativeQuery = true)
    List<Long> findAllPessoaFisica(Long idGrupo);
}
