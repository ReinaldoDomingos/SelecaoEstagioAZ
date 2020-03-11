package br.com.selecao.locadora.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompradorId implements Serializable {
    @Column(name = "EMPRESA")
    private Long empresaId;

    @Column(name = "LEILAO")
    private Long leilaoId;

    public CompradorId() {
    }

    public CompradorId(Long empresa, Long leilao) {
        this.empresaId = empresa;
        this.leilaoId = leilao;
    }

    public Long getEmpresa() {
        return empresaId;
    }

    public void setEmpresa(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getLeilao() {
        return leilaoId;
    }

    public void setLeilao(Long leilaoId) {
        this.leilaoId = leilaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompradorId compradorId = (CompradorId) o;
        return empresaId.equals(compradorId.empresaId) &&
                leilaoId.equals(compradorId.leilaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresaId, leilaoId);
    }
}
