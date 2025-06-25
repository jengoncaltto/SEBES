package br.uniriotec.prae.sebes.Entity;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;


@Entity
@Table (name = "bolsa")

public class Bolsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)



    @JsonProperty("id")
    @Column(name = "id")
    private Integer idBolsa;
    
    
    private String tipo;
    private BigDecimal valor;


    public Bolsa(){}


    public Integer getIdBolsa() {
        return idBolsa;
    }


    public void setIdBolsa(Integer idBolsa) {
        this.idBolsa = idBolsa;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public BigDecimal getValor() {
        return valor;
    }


    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    
}
