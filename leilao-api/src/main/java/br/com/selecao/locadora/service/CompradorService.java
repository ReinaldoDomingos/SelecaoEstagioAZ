package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.CompradorBO;
import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.CompradorId;
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

    @RequestMapping(value = "/compradores", method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(compradorBO.buscarTodos());
    }

    @RequestMapping(value = "/comprador/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comprador> buscarComprador(@PathVariable("id") @NotNull @DecimalMin("1") Long compradorId) {
        return ResponseEntity.ok().body(compradorBO.buscarCompradorPorId(compradorId));
    }

    @RequestMapping(value = "/comprador", method = RequestMethod.POST)
    public ResponseEntity addComprador(@Valid @RequestBody String comprador) {
        Comprador comprador1 = new Comprador().fromJSON(comprador);
        if (comprador1.getEmpresa() != null && comprador1.getLeilao() != null) {
            return ResponseEntity.ok().body(compradorBO.add(comprador1));
        }
        return ResponseEntity.ok().body("{\"erro\": \"Violação de Integridade\"}");
    }

    @RequestMapping(value = "/comprador/{id}", method = RequestMethod.DELETE)
    public void deleteComprador(@PathVariable("id") @NotNull @DecimalMin("1") Long compradorId) {
        Comprador comprador = compradorBO.buscarCompradorPorId(compradorId);
        if (comprador != null)
            compradorBO.delete(new CompradorId(comprador.getEmpresa(), comprador.getLeilao()));
    }
}
