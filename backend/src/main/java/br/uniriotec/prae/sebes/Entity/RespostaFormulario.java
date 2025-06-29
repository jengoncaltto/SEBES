package br.uniriotec.prae.sebes.Entity;

import java.time.LocalDateTime;

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
    private LocalDateTime dataEnvio;

    @Column(name = "tipo_forms")
    private String tipoForms;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private String status;

    @Column(name = "id_resposta_associada")
    private String idRespostaAssociada;

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
