package br.uniriotec.prae.sebes.Entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "bolsa")
public class Bolsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 255)
    @JsonProperty("nome")
    private String nome;

    @Column(name = "descricao", nullable = false, length = 500)
    @JsonProperty("descricao")
    private String descricao;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    @JsonProperty("valor")
    private BigDecimal valor;

    @Column(name = "periodo", nullable = false)
    @JsonProperty("periodo")
    private Integer periodo;

    public Bolsa() {}

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }
}
