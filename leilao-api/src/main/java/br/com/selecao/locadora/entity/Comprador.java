package br.com.selecao.locadora.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_COMPRADOR")
public class Comprador implements Serializable {

    @Column(name = "ID")
    private Long id;

    @JsonIgnore
    @EmbeddedId
    CompradorId compradorId;

    public Comprador() {
        compradorId = new CompradorId();
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpresa() {
        return this.compradorId.getEmpresa();
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

    public Comprador fromJSON(String json) {
        Comprador comprador = null;
        try {
            comprador = new Comprador();
            String[] elementos = json.replaceAll(" ", "")
                    .replaceAll("\t", "")
                    .replaceAll("\r\n", "")
                    .replace("{", "")
                    .replace("}", "").split(",");
            for (int i = 0; i < elementos.length; i++) {
                if (elementos[i].indexOf("empresa\":") != -1)
                    comprador.setEmpresa(Long.parseLong(elementos[i].split(":")[1]));
                else if (elementos[i].indexOf("leilao\":") != -1)
                    comprador.setLeilao(Long.parseLong(elementos[i].split(":")[1]));
            }
        } catch (Exception err) {
        }
        return comprador;
    }

    public String getValor(String itemJSON, String itemNome) {
        return itemJSON.replaceAll(" ", "")
                .replaceAll("\"", "")
                .replaceAll(itemNome, "")
                .replaceAll("\t", "")
                .replaceAll("\r\n", "")
                .replaceAll(":", "");
    }
}