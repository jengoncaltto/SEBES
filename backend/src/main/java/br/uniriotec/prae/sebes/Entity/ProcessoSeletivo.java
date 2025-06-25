package br.uniriotec.prae.sebes.Entity;

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
@Table(name = "processo_seletivo")
public class ProcessoSeletivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @JsonProperty("id")
    @Column(name = "id")
    private Integer id;

    @JsonProperty("data_inicio")
    @Column(name = "data_inicio")
    private Timestamp dataInicio;


    @JsonProperty("data_encerramento")
    @Column(name = "data_encerramento")
    private Timestamp dataEncerramento; 


    @JsonProperty("status")
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_bolsa", referencedColumnName = "id")
    private Bolsa idBolsa;


    public ProcessoSeletivo(){}

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setIdBolsa(Bolsa idBolsa) {
        this.idBolsa = idBolsa;
    }

    public Bolsa getIdBolsa() {
        return idBolsa;
    }

    
}
