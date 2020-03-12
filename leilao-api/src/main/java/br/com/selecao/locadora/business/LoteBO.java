package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoteBO {

    @Autowired
    private LoteRepository loteRepository;

    public List<Lote> buscarTodos() {
        return loteRepository.findAll();
    }

    public Lote buscarLote(Long id) {
        return loteRepository.findOne(id);
    }

    public void add(Lote lote) {
        Lote lote1 = new Lote();
        lote1.setNumeroLote(lote.getNumeroLote());
        lote1.setDescricao(lote.getDescricao());
        lote1.setQuantidade(lote.getQuantidade());
        lote1.setValorInicial(lote.getValorInicial());
        lote1.setUnidade(lote.getUnidade());
        lote1.setLeilao(lote.getLeilao());
        loteRepository.saveAndFlush(lote1);
    }

    public void update(Long id, Lote lote) {
        Lote lote1 = new Lote();
        lote1.setId(lote.getId());
        lote1.setNumeroLote(lote.getNumeroLote());
        lote1.setDescricao(lote.getDescricao());
        lote1.setQuantidade(lote.getQuantidade());
        lote1.setValorInicial(lote.getValorInicial());
        lote1.setUnidade(lote.getUnidade());
        lote1.setLeilao(lote.getLeilao());
        lote1.setCreatedAt(lote.getCreatedAt());
        lote1.setUpdatedAt(new Date());
        loteRepository.saveAndFlush(lote1);
    }

    public void delete(Long id) {
        loteRepository.delete(id);
    }

}
