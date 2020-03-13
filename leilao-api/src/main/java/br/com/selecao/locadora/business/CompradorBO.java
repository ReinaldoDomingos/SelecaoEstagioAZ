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

    public Comprador buscarComprador(Long id) {
        CompradorId compradorId = null;
        List<Comprador> todos = buscarTodos();
//        for (int i = 0; i < todos.size(); i++) {
//            if (todos.get(i).getId().equals(id))
//                compradorId = new CompradorId(todos.get(i).getEmpresa(), todos.get(i).getLeilao());
//        }
        return compradorRepository.findOne(compradorId);
    }

    public void add(Comprador comprador) {
        Comprador comprador1 = new Comprador();
        comprador1.setEmpresa(comprador.getEmpresa());
        comprador1.setLeilao(comprador.getLeilao());
        compradorRepository.saveAndFlush(comprador1);
    }

    public void update(Long id, Comprador comprador) {
        CompradorId compradorId = null;
        List<Comprador> todos = buscarTodos();
//        for (int i = 0; i < todos.size(); i++) {
//            if (todos.get(i).getId().equals(id))
//                compradorId = new CompradorId(todos.get(i).getEmpresa(), todos.get(i).getLeilao());
//        }
        Comprador comprador1 = new Comprador();
//        comprador1.setId(comprador.getId());
        comprador1.setEmpresa(comprador.getEmpresa());
        comprador1.setLeilao(comprador.getLeilao());
        compradorRepository.saveAndFlush(comprador1);
    }

    public void delete(Long id) {
        CompradorId compradorId = null;
        List<Comprador> todos = buscarTodos();
//        for (int i = 0; i < todos.size(); i++) {
//            if (todos.get(i).getId().equals(id))
//                compradorId = new CompradorId(todos.get(i).getEmpresa(), todos.get(i).getLeilao());
//        }
        compradorRepository.delete(compradorId);
    }
}
