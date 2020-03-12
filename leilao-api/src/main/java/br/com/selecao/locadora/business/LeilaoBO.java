package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeilaoBO {

    @Autowired
    private LeilaoRepository leilaoRepository;

    public List<Leilao> buscarTodos() {
        return leilaoRepository.findAll();
    }

    public Leilao buscarLeilao(Long id) {
        return leilaoRepository.findOne(id);
    }

    public void add(Leilao leilao) {
        Leilao leilao1 = new Leilao();
        leilao1.setCodigo(leilao.getCodigo());
        leilao1.setDescricao(leilao.getDescricao());
        leilao1.setVendedor(leilao.getVendedor());
        leilao1.setInicioPrevisto(leilao.getInicioPrevisto());
        leilaoRepository.saveAndFlush(leilao1);
    }

    public void update(Long id, Leilao leilao) {
        Leilao leilao1 = new Leilao();
        leilao1.setId(id);
        leilao1.setCodigo(leilao.getCodigo());
        leilao1.setDescricao(leilao.getDescricao());
        leilao1.setVendedor(leilao.getVendedor());
        leilao1.setInicioPrevisto(leilao.getInicioPrevisto());
        leilao1.setCreatedAt(leilao.getCreatedAt());
        leilao1.setUpdatedAt(new Date());
        leilaoRepository.saveAndFlush(leilao1);
    }

    public void delete(Long id) {
        leilaoRepository.delete(id);
    }

}
