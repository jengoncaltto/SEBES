package br.uniriotec.prae.sebes.dto;

import java.time.LocalDateTime;

public class RespostaFormularioDTO {
    private String id;
    private LocalDateTime dataEnvio;
    private String tipoForms;
    private String conteudo;
    private String status;
    private String idRespostaAssociada;
    private String idUsuario;
    private String idProcessoSeletivo;

    public RespostaFormularioDTO() {
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getTipoForms() {
        return tipoForms;
    }

    public void setTipoForms(String tipoForms) {
        this.tipoForms = tipoForms;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdRespostaAssociada() {
        return idRespostaAssociada;
    }

    public void setIdRespostaAssociada(String idRespostaAssociada) {
        this.idRespostaAssociada = idRespostaAssociada;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdProcessoSeletivo() {
        return idProcessoSeletivo;
    }

    public void setIdProcessoSeletivo(String idProcessoSeletivo) {
        this.idProcessoSeletivo = idProcessoSeletivo;
    }
}
