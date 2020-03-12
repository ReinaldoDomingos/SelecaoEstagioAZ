package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmpresaBO {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> buscarTodos() {
        return empresaRepository.findAll();
    }

    public Empresa buscarEmpresa(Long id) {
        return empresaRepository.findOne(id);
    }

    public void add(Empresa empresa) {
        Empresa empresa1 = new Empresa();
        empresa1.setRazaoSocial(empresa.getRazaoSocial());
        empresa1.setCnpj(empresa.getCnpj());
        empresa1.setLogradouro(empresa.getLogradouro());
        empresa1.setMunicipio(empresa.getMunicipio());
        empresa1.setNumero(empresa.getNumero());
        empresa1.setComplemento(empresa.getComplemento());
        empresa1.setBairro(empresa.getBairro());
        empresa1.setCep(empresa.getCep());
        empresa1.setTelefone(empresa.getTelefone());
        empresa1.setEmail(empresa.getEmail());
        empresa1.setSite(empresa.getSite());
        empresa1.setUsuario(empresa.getUsuario());
        empresa1.setSenha(empresa.getSenha());
        empresaRepository.saveAndFlush(empresa1);
    }

    public void update(Long id, Empresa empresa) {
        Empresa empresa1 = new Empresa();
        empresa1.setId(id);
        empresa1.setRazaoSocial(empresa.getRazaoSocial());
        empresa1.setCnpj(empresa.getCnpj());
        empresa1.setLogradouro(empresa.getLogradouro());
        empresa1.setMunicipio(empresa.getMunicipio());
        empresa1.setNumero(empresa.getNumero());
        empresa1.setComplemento(empresa.getComplemento());
        empresa1.setBairro(empresa.getBairro());
        empresa1.setCep(empresa.getCep());
        empresa1.setTelefone(empresa.getTelefone());
        empresa1.setEmail(empresa.getEmail());
        empresa1.setSite(empresa.getSite());
        empresa1.setUsuario(empresa.getUsuario());
        empresa1.setSenha(empresa.getSenha());
        empresa1.setCreatedAt(empresa.getCreatedAt());
        empresa1.setUpdatedAt(new Date());
        empresaRepository.saveAndFlush(empresa1);
    }

    public void delete(Long id) {
        empresaRepository.delete(id);
    }

}
