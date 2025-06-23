package com.example.sebes.Entity;

import java.security.Timestamp;

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
@Table(name = "etapa")
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    
    @JsonProperty("id")
    @Column(name = "id") 
    private Integer id;


    @JsonProperty("tipo_etada")
    @Column(name = "tipo_etapa") 
    private String tipoEtapa;

    private String status;
    
    
    @ManyToOne
    @JoinColumn(name = "id_processo_seletivo", referencedColumnName = "id")
    private ProcessoSeletivo idProcessoSeletivo;
    
    @JsonProperty("data_inicio")
    @Column(name = "data_inicio") 
    private Timestamp dataInicio;
    
    @JsonProperty("data_encerramento")
    @Column(name = "data_encerramento") 
    private Timestamp dataEncerramento;



    public Etapa(){}

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getTipoEtapa() {
        return tipoEtapa;
    }


    public void setTipoEtapa(String tipoEtapa) {
        this.tipoEtapa = tipoEtapa;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public ProcessoSeletivo getProcessoSeletivo() {
        return idProcessoSeletivo;
    }

    public void setProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
    this.idProcessoSeletivo = processoSeletivo;
}

    public Timestamp getDataInicio() {
        return dataInicio;
    }


    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }


    public Timestamp getDataEncerramento() {
        return dataEncerramento;
    }


    public void setDataEncerramento(Timestamp dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

}
