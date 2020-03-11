package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteBO {

    @Autowired
    private LoteRepository loteRepository;

    public List<Lote> buscarTodos() {
        return loteRepository.findAll();
    }
}
