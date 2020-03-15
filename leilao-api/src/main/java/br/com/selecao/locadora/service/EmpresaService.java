package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.EmpresaBO;
import br.com.selecao.locadora.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@RestController
public class EmpresaService {

    @Autowired
    private EmpresaBO empresaBO;

    @RequestMapping(value = "/empresas", method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(empresaBO.buscarTodos());
    }

    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable("id") @NotNull @DecimalMin("0") Long empresaId) {
        return ResponseEntity.ok().body(empresaBO.buscarEmpresa(empresaId));
    }

    @RequestMapping(value = "/empresa", method = RequestMethod.POST)
    public void addEmpresa(@Valid @RequestBody Empresa empresa) {
        empresaBO.add(empresa);
    }

    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") @NotNull @DecimalMin("0") Long empresaId, @RequestBody Empresa empresa) {
        empresaBO.update(empresaId, empresa);
    }

    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.DELETE)
    public void deleteEmpresa(@PathVariable("id") @NotNull @DecimalMin("0") Long empresaId) {
        empresaBO.delete(empresaId);
    }
}