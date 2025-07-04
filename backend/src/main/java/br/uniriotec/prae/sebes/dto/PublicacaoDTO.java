package br.uniriotec.prae.sebes.dto;

import java.time.LocalDateTime;

public class PublicacaoDTO {

    private Integer id;
    private LocalDateTime dataPublicacao;
    private String conteudo;
    private String idUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
