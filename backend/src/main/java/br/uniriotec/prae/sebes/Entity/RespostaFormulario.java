package br.uniriotec.prae.sebes.Entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resposta_formulario")
public class RespostaFormulario {
    @Id
    private String id;

    @Column(name = "data_envio")
    private Timestamp dataEnvio;

    @Column(name = "tipo_forms")
    private String tipoForms;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private String status;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "id_processo_seletivo")
    private String idProcessoSeletivo;

    // Construtor padrão
    public RespostaFormulario() {}

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Timestamp dataEnvio) {
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
