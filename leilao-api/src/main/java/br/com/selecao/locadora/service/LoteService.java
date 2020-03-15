package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LoteBO;
import br.com.selecao.locadora.entity.Lote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@RestController
public class LoteService {

    @Autowired
    private LoteBO loteBO;

    @RequestMapping(value = "/lotes", method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(loteBO.buscarTodos());
    }

    @RequestMapping(value = "/lote/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lote> buscarLote(@PathVariable("id") @NotNull @DecimalMin("1") Long loteId) {
        return ResponseEntity.ok().body(loteBO.buscarLote(loteId));
    }

    @RequestMapping(value = "/lote", method = RequestMethod.POST)
    public void addLote(@Valid @RequestBody Lote lote) {
        loteBO.add(lote);
    }

    @RequestMapping(value = "/lote/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") @NotNull @DecimalMin("1") Long loteId, @RequestBody Lote lote) {
        loteBO.update(loteId, lote);
    }

    @RequestMapping(value = "/lote/{id}", method = RequestMethod.DELETE)
    public void deleteLote(@PathVariable("id") @NotNull @DecimalMin("0") Long loteId) {
        loteBO.delete(loteId);
    }
}