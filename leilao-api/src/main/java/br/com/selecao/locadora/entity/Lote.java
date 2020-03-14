package br.com.selecao.locadora.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LEILAO", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Leilao leilao_lote_fk;

    @Column(name = "NUMERO_LOTE")
    private Long numeroLote;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "VALOR_INICIAL")
    private Integer valorInicial;

    @Column(name = "UNIDADE")
    private String unidade;

//    @OneToMany
//    @ManyToOne
//    @Column(name = "LEILAO_LOTE_FK")
//    private Leilao leilao;


    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(Long codigo) {
        this.numeroLote = codigo;
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

    public Long getLeilaoId() {
        return leilao_lote_fk.getId();
    }

    @JsonIgnore
    public Leilao getLeilao() {
        return leilao_lote_fk;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao_lote_fk = leilao;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updateAt) {
        this.updatedAt = updateAt;
    }


    @PrePersist
    void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
        if (this.updatedAt == null) updatedAt = new Date();
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
