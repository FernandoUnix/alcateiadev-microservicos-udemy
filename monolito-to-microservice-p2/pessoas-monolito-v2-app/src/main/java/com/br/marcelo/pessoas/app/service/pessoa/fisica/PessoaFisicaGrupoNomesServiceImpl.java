package com.br.marcelo.pessoas.app.service.pessoa.fisica;

import com.br.marcelo.pessoas.app.repository.pessoa.fisica.PessoaFisicaGrupoRepository;
import com.br.marcelo.pessoas.app.repository.pessoa.fisica.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaFisicaGrupoNomesServiceImpl implements PessoaFisicaGrupoNomesService {

    @Autowired
    private PessoaFisicaGrupoRepository pessoaFisicaGrupoRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Transactional
    @Override
    public void atualizarNomesGrupos(Long idPessoaFisica){
        List<String> listaNomes = pessoaFisicaGrupoRepository.findGruposNome(idPessoaFisica);
        String grupos = montarNome(listaNomes);
        listaNomes = null;

        pessoaFisicaRepository.atualizarNomesGrupo(idPessoaFisica, grupos);
    }

    @Transactional
    @Override
    public void atualizarNomesGruposAllPF(Long idGrupo) {
        List<Long> lista = pessoaFisicaGrupoRepository.findAllPessoaFisica(idGrupo);

        if( lista == null ){
            return;
        }

        for(Long idPessoaFisica : lista){
            List<String> listaNomes = pessoaFisicaGrupoRepository.findGruposNome(idPessoaFisica);
            String grupos = montarNome(listaNomes);
            listaNomes = null;
            pessoaFisicaRepository.atualizarNomesGrupo(idPessoaFisica, grupos);
        }

    }

    private String montarNome(List<String> listaNomes) {

        if( listaNomes == null ){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(int loop=0; loop < listaNomes.size(); loop++){
            String item = listaNomes.get(loop);
            sb.append(item);
            if( loop < listaNomes.size()-1 ){
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
