package br.uniriotec.prae.sebes.Entity;

import java.security.Timestamp;

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
	@GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "tipo_etapa")
    private String tipoEtapa;

    @Column(name = "data_inicio")
    private Timestamp dataInicio;

    @Column(name = "data_encerramento")
    private Timestamp dataEncerramento;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_processo_seletivo")
    private ProcessoSeletivo processoSeletivo;

    public Etapa(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return processoSeletivo;
    }

    public void setProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
    this.processoSeletivo = processoSeletivo;
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
