package br.uniriotec.prae.sebes.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false, length = 36)
	private String id;

	@JsonProperty("nome")
	@Column(name = "nome")
	private String nome;

	@JsonProperty("nome_social")
	@Column(name = "nome_social")
	private String nomeSocial;

	@JsonProperty("email")
	@Column(name = "email")
	private String email;

	@JsonProperty("email_recuperacao")
	@Column(name = "email_recuperacao")
	private String emailRecuperacao;

	@JsonProperty("telefone")
	@Column(name = "telefone")
	private String telefone;

	@Column(name = "status")
	public boolean status = true;

	public Usuario() {
	} // Construtor

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailRecuperacao() {
		return emailRecuperacao;
	}

	public void setEmailRecuperacao(String emailRecuperacao) {
		this.emailRecuperacao = emailRecuperacao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean novoStatus) {
		this.status = novoStatus;
	}
}
