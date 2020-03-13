package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.UnidadeBO;
import br.com.selecao.locadora.entity.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@RestController
public class UnidadeService {

    @Autowired
    private UnidadeBO unidadeBO;

    @RequestMapping(value = "/unidades", method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(unidadeBO.buscarTodos());
    }

    @RequestMapping(value = "/unidades/{id}", method = RequestMethod.GET)
    public ResponseEntity<Unidade> buscarUnidade(@PathVariable("id") @NotNull @DecimalMin("0") Long unidadeId) {
        return ResponseEntity.ok().body(unidadeBO.buscarUnidade(unidadeId));
    }

    @RequestMapping(name = "/unidades", method = RequestMethod.POST)
    public void add(@Valid @RequestBody Unidade unidade) {
        unidadeBO.add(unidade);
    }

    @RequestMapping(value = "/unidades/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") @NotNull @DecimalMin("0") Long unidadeId, @RequestBody Unidade unidade) {
        System.out.println("update " + unidadeId);
        unidadeBO.update(unidadeId, unidade);
    }

    @RequestMapping(value = "/unidades/{id}", method = RequestMethod.DELETE)
    public void deleteUnidade(@PathVariable("id") @NotNull @DecimalMin("0") Long unidadeId) {
        unidadeBO.delete(unidadeId);
    }
}