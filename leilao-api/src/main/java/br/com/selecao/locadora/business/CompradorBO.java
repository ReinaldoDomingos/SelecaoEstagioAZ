package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.CompradorId;
import br.com.selecao.locadora.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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

    public Comprador buscarCompradorPorId(Long id) {
        CompradorId compradorId = null;
        List<Comprador> todos = buscarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId().equals(id))
                compradorId = new CompradorId(todos.get(i).getEmpresa(), todos.get(i).getLeilao());
        }

        return buscarComprador(compradorId);
    }

    public Object add(Comprador comprador) {
        List<Comprador> todos = buscarTodos();
        Long maiorId = 1L;
        Comprador compradorExistente = buscarComprador(new CompradorId(comprador.getEmpresa(), comprador.getLeilao()));
        System.out.println(compradorExistente);
        if (compradorExistente == null) {
            for (int i = 0; i < todos.size(); i++) {
                Long id = todos.get(i).getId();
                if (id > maiorId) {
                    maiorId = id;
                }
            }
            System.out.println(maiorId);
            Comprador comprador1 = new Comprador();
            comprador1.setId(maiorId + 1);
            comprador1.setEmpresa(comprador.getEmpresa());
            comprador1.setLeilao(comprador.getLeilao());
            try {

                Comprador resultado = compradorRepository.saveAndFlush(comprador1);
                if (resultado != null)
                    return "{\"status\":200'}";
                else
                    return "{\"status\":400'}";
            } catch (DataIntegrityViolationException exception) {
                return "{\"erro\": \"Violação de Integridade\"}";
            }
        }
        return "{\"status\":400}";
    }

    public void delete(CompradorId compradorId) {
        compradorRepository.delete(compradorId);
    }
}
