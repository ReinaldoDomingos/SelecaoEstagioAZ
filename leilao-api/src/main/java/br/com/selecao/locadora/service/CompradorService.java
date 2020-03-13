package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.CompradorBO;
import br.com.selecao.locadora.entity.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@RestController
public class CompradorService {

    @Autowired
    private CompradorBO compradorBO;

    @RequestMapping(value = "/comprador", method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(compradorBO.buscarTodos());
    }
/*

    @RequestMapping(value = "/comprador/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comprador> buscarComprador(@PathVariable("id") @NotNull @DecimalMin("0") Long compradorId) {
        return ResponseEntity.ok().body(compradorBO.buscarComprador(compradorId));
    }

    @RequestMapping(value = "/comprador", method = RequestMethod.POST)
    public void addComprador(@Valid @RequestBody Comprador comprador) {
        compradorBO.add(comprador);
    }

    @RequestMapping(value = "/comprador/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") @NotNull @DecimalMin("0") Long compradorId, @RequestBody Comprador comprador) {
        compradorBO.update(compradorId, comprador);
    }

    @RequestMapping(value = "/comprador/{id}", method = RequestMethod.DELETE)
    public void deleteComprador(@PathVariable("id") @NotNull @DecimalMin("0") Long compradorId) {
        compradorBO.delete(compradorId);
    }
*/
}