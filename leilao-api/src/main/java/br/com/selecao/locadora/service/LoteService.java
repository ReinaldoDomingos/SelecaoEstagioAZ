package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LoteBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lotes")
public class LoteService {

    @Autowired
    private LoteBO loteBO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(loteBO.buscarTodos());
    }
}