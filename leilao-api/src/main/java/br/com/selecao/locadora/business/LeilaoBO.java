package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeilaoBO {

    @Autowired
    private LeilaoRepository leilaoRepository;

    public List<Leilao> buscarTodos() {
        return leilaoRepository.findAll();
    }
}
