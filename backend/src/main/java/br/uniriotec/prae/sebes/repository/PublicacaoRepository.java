package br.uniriotec.prae.sebes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    List<Publicacao> findAllByUsuario_Id(String idUsuario);

    List<Publicacao> findAllByDataPublicacao(LocalDateTime dataPublicacao);

}
