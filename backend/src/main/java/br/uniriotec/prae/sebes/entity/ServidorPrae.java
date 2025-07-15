package br.uniriotec.prae.sebes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servidor_prae")
public class ServidorPrae {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "nome_social")
    private String nomeSocial;

    @Column(nullable = false)
    private String setor;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(name = "id_usuario", length = 36, nullable = false)
    private String idUsuario;

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

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
