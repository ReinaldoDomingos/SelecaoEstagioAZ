package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UnidadeBO {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> buscarTodos() {
        return unidadeRepository.findAll();
    }

    public Unidade buscarUnidade(Long id) {
        return unidadeRepository.findOne(id);
    }

    public void add(Unidade unidade) {
        Unidade unidade1 = new Unidade();
        unidade1.setNome(unidade.getNome());
        unidadeRepository.saveAndFlush(unidade1);
    }

    public void update(Long id, Unidade unidade) {
        Unidade unidade1 = new Unidade();
        unidade1.setId(id);
        unidade1.setNome(unidade.getNome());
        unidade1.setCreatedAt(unidade.getCreateAt());
        unidade1.setUpdatedAt(new Date());
        unidadeRepository.saveAndFlush(unidade1);
    }

    public void delete(Long id) {
        unidadeRepository.delete(id);
    }
}
