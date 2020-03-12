package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.entity.Leilao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@RestController
public class LeilaoService {

    @Autowired
    private LeilaoBO leilaoBO;

    @RequestMapping(value = "/leilao", method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(leilaoBO.buscarTodos());
    }

    @RequestMapping(value = "/leilao/{id}", method = RequestMethod.GET)
    public ResponseEntity<Leilao> buscarLeilao(@PathVariable("id") @NotNull @DecimalMin("0") Long leilaoId) {
        return ResponseEntity.ok().body(leilaoBO.buscarLeilao(leilaoId));
    }

    @RequestMapping(value = "/leilao", method = RequestMethod.POST)
    public void addLeilao(@Valid @RequestBody Leilao leilao) {
        leilaoBO.add(leilao);
    }

    @RequestMapping(value = "/leilao/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") @NotNull @DecimalMin("0") Long leilaoId, @RequestBody Leilao leilao) {
        leilaoBO.update(leilaoId, leilao);
    }

    @RequestMapping(value = "/leilao/{id}", method = RequestMethod.DELETE)
    public void deleteLeilao(@PathVariable("id") @NotNull @DecimalMin("0") Long leilaoId) {
        leilaoBO.delete(leilaoId);
    }
}