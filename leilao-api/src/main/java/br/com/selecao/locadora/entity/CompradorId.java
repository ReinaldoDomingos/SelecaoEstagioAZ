package br.com.selecao.locadora.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompradorId implements Serializable {
    @Column(name = "EMPRESA")
    private Long empresa;

    @Column(name = "LEILAO")
    private Long leilao;

    public CompradorId() {
    }

    public CompradorId(Long empresa, Long leilao) {
        this.empresa = empresa;
        this.leilao = leilao;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }

    public Long getLeilao() {
        return leilao;
    }

    public void setLeilao(Long leilao) {
        this.leilao = leilao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompradorId compradorId = (CompradorId) o;
        return empresa.equals(compradorId.empresa) &&
                leilao.equals(compradorId.leilao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresa, leilao);
    }
}