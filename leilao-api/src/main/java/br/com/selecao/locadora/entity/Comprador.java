package br.com.selecao.locadora.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_COMPRADOR")
public class Comprador implements Serializable {

/*
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_comprador")
    private Long id;
*/

    @EmbeddedId
    CompradorId compradorId;
/*

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
*/

    public Long getEmpresa() {
        return compradorId.getEmpresa();
    }

    public void setEmpresa(Long empresa) {
        this.compradorId.setEmpresa(empresa);
    }

    public Long getLeilao() {
        return compradorId.getLeilao();
    }

    public void setLeilao(Long leilao) {
        this.compradorId.setLeilao(leilao);
    }
}