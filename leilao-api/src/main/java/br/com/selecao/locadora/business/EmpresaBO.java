package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaBO {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> buscarTodos() {
        return empresaRepository.findAll();
    }
}
