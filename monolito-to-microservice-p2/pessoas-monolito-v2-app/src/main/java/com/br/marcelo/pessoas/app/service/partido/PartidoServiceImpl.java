package com.br.marcelo.pessoas.app.service.partido;

import com.br.marcelo.pessoas.app.entity.partido.Partido;
import com.br.marcelo.pessoas.app.json.paginacao.PagResultJson;
import com.br.marcelo.pessoas.app.repository.partido.PartidoInputSelectSpecification;
import com.br.marcelo.pessoas.app.repository.partido.PartidoRepository;
import com.br.marcelo.pessoas.app.repository.partido.PartidoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public Partido getById(Long id) {
        Partido partido = new Partido();
        partido.setId(id);

        Specification<Partido> spec = new PartidoSpecification(partido);

        return partidoRepository.findOne(spec);
    }

    @Override
    public Page<Partido> consulta(Partido partido, int pos, String ordenacao) throws Exception {
        Specification<Partido> spec = new PartidoSpecification(partido.getSigla(), ordenacao);
        return partidoRepository.findAll(spec, new PageRequest(pos, PagResultJson.NRO_TOTAL_REGISTROS));
    }

    @Transactional
    @Override
    public void incluir(Partido partido) throws Exception {

        if (isSiglaExiste(partido.getSigla())) {
            throw new Exception("Sigla já existe.");
        }

        partidoRepository.save(partido);
    }

    @Transactional
    @Override
    public void alterar(Partido partido) throws Exception {
        if (isSiglaExiste(partido.getSigla(), partido.getId())) {
            throw new Exception("Sigla já existe.");
        }

        Partido partidoBanco = partidoRepository.findOne(partido.getId());
        partidoBanco.setSigla(partido.getSigla());
        partidoBanco.setDescricao(partido.getDescricao());

        partidoRepository.save(partidoBanco);
    }

    @Transactional
    @Override
    public void excluir(Long id) throws Exception {
        Partido partido = new Partido();
        partido.setId(id);

        Specification<Partido> spec = new PartidoSpecification(partido);

        partido = partidoRepository.findOne(spec);
        partidoRepository.delete(partido);
    }

    private boolean isSiglaExiste(String sigla) {
        return partidoRepository.getSigla(sigla) > 0;
    }

    private boolean isSiglaExiste(String sigla, Long id) {
        return partidoRepository.getSigla(sigla, id) > 0;
    }

    @Override
    public Page<Partido> consultaComponente(String codigo, String nome,
                                            Long ignorar, int pos) {

        Partido bean = new Partido();
        bean.setSigla(codigo);
        bean.setDescricao(nome);

        PartidoInputSelectSpecification spec = new PartidoInputSelectSpecification(bean);

        return partidoRepository.findAll(spec, new PageRequest(pos,
                PagResultJson.NRO_TOTAL_REGISTROS));

    }
}
