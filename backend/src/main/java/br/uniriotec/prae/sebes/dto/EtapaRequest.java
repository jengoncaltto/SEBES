package br.uniriotec.prae.sebes.dto;

import java.time.LocalDateTime;

public class EtapaRequest {
    private String tipoEtapa;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEncerramento;
    private String status;
    private String idProcessoSeletivo;

    // Getters e Setters
    public String getTipoEtapa() {
        return tipoEtapa;
    }

    public void setTipoEtapa(String tipoEtapa) {
        this.tipoEtapa = tipoEtapa;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdProcessoSeletivo() {
        return idProcessoSeletivo;
    }

    public void setIdProcessoSeletivo(String idProcessoSeletivo) {
        this.idProcessoSeletivo = idProcessoSeletivo;
    }
}
