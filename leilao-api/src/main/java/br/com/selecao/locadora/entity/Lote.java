package br.com.selecao.locadora.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE")
@SequenceGenerator(name = "seq_leilao", sequenceName = "seq_leilao")
public class Lote implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_leilao")
    private Long id;

    @Column(name = "NUMERO_LOTE")
    private Long codigo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "VALOR_INICIAL")
    private Integer valorInicial;

    @Column(name = "UNIDADE")
    private String unidade;

    @Column(name = "LEILAO")
    private Long leilao;

    @Column(name = "CREATED_AT")
    private Date createAt;

    @Column(name = "UPDATED_AT")
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Integer valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Long getLeilao() {
        return leilao;
    }

    public void setLeilao(Long leilao) {
        this.leilao = leilao;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @PrePersist
    void prePersist() {
        if (this.createAt == null) createAt = new Date();
        if (this.updateAt == null) updateAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lote leilao = (Lote) o;
        return id.equals(leilao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
