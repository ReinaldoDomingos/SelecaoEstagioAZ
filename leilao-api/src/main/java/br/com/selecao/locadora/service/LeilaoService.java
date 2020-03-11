package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LeilaoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/leiloes")
public class LeilaoService {

    @Autowired
    private LeilaoBO leilaoBO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(leilaoBO.buscarTodos());
    }
}