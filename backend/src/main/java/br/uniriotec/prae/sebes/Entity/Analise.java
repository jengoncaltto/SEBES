package com.example.sebes.Entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table (name = "analise")
public class Analise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @JsonProperty("id")
    @Column(name = "id")
    private Integer id; // auto_increment = Integer
    
    
    @JsonProperty("data_analise")
    @Column(name = "data_analise")
    private Timestamp dataAnalise;
    


    
    @JsonProperty("data_avaliacao")
    @Column(name = "data_avaliacao")
    private Timestamp dataAvaliacao;
    

   
    @ManyToOne
    @JoinColumn(name = "id_servidor_praxe", referencedColumnName = "id")
    private ServidorPrae idServidorPraxe;

    
    public ServidorPrae getIdServidorPraxe() {
        return idServidorPraxe;
    }

    public void setIdServidorPraxe(ServidorPrae idServidorPraxe) {
        this.idServidorPraxe = idServidorPraxe;
    }

    @JsonProperty("status")
    @Column(name = "status")
    private String status;

    @JsonProperty("resultado")
    @Column(name = "resultado")
    private String resultado;


    public Analise(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(Timestamp dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public Timestamp getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Timestamp dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
