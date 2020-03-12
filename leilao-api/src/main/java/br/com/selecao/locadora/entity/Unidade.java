package br.com.selecao.locadora.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_UNIDADE")
@SequenceGenerator(name = "seq_unidade", sequenceName = "seq_unidade")
public class Unidade implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "seq_unidade")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createAt;

    @Column(name = "UPDATED_AT")
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createAt = createAt;
    }

    @PrePersist
    void prePersist() {
        if (this.createAt == null) createAt = new Date();
        if (this.updateAt == null) updateAt = new Date();
    }


    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdatedAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unidade unidade = (Unidade) o;
        return id.equals(unidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
