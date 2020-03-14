package br.com.selecao.locadora.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_LEILAO")
@SequenceGenerator(name = "seq_leilao", sequenceName = "seq_leilao")
public class Leilao implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_leilao")
    private Long id;

//    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Lote> listaMovimentosAdesao = new ArrayList<>();

    @Column(name = "CODIGO")
    private Long codigo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "VENDEDOR")
    private Long vendedor;

    @Column(name = "INICIO_PREVISTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioPrevisto;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String codigo) {
        this.descricao = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getVendedor() {
        return vendedor;
    }

    public void setVendedor(Long vendedor) {
        this.vendedor = vendedor;
    }

    public Date getInicioPrevisto() {
        return inicioPrevisto;
    }

    public void setInicioPrevisto(Date inicioPrevisto) {
        this.inicioPrevisto = inicioPrevisto;
    }

//    public Lote getLote() {
//        return lote;
//    }

//    public void setLote(Lote lote) {
//        this.lote = lote;
//    }

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
        Leilao leilao = (Leilao) o;
        return id.equals(leilao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
