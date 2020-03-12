package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.CompradorId;
import br.com.selecao.locadora.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompradorBO {

    @Autowired
    private CompradorRepository compradorRepository;

    public List<Comprador> buscarTodos() {
        return compradorRepository.findAll();
    }

    public Comprador buscarComprador(CompradorId id) {
        return compradorRepository.findOne(id);
    }

    public void add(Comprador comprador) {
        Comprador comprador1 = new Comprador();
        comprador1.setEmpresa(comprador.getEmpresa());
        comprador1.setLeilao(comprador.getLeilao());
        compradorRepository.saveAndFlush(comprador1);
    }

    public void update(CompradorId id, Comprador comprador) {
        Comprador comprador1 = new Comprador();
        comprador1.setLeilao(id.getLeilao());
        comprador1.setEmpresa(id.getEmpresa());
        compradorRepository.saveAndFlush(comprador1);
    }

    public void delete(CompradorId id) {
        compradorRepository.delete(id);
    }
}
