package br.uniriotec.prae.sebes.Entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscricao")
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    
    @JsonProperty("id" )
    @Column(name = "id")
    private Integer id;

    @JsonProperty("data_envio" )
    @Column(name = "data_envio")
    private Timestamp dataEnvio;


    @JsonProperty("status" )
    @Column(name = "status")
    private String status;


    @JsonProperty("id_estudante")
    @Column(name = "id_estudante")
    private Integer idEstudante;




    @JsonProperty("id_processo_seletivo")
    @Column(name = "id_processo_seletivo")
    private Integer idProcessoSeletivo;

    public Inscricao(){}



    @JsonProperty("id_analise" )
    @Column(name = "id_analise")
    private Integer idAnalise;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Timestamp getDataEnvio() {
        return dataEnvio;
    }


    public void setDataEnvio(Timestamp dataEnvio) {
        this.dataEnvio = dataEnvio;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getIdEstudante() {
        return idEstudante;
    }


    public void setIdEstudante(Integer idEstudante) {
        this.idEstudante = idEstudante;
    }


    public Integer getIdProcessoSeletivo() {
        return idProcessoSeletivo;
    }


    public void setIdProcessoSeletivo(Integer idProcessoSeletivo) {
        this.idProcessoSeletivo = idProcessoSeletivo;
    }


    public Integer getIdAnalise() {
        return idAnalise;
    }


    public void setIdAnalise(Integer idAnalise) {
        this.idAnalise = idAnalise;
    }

}
