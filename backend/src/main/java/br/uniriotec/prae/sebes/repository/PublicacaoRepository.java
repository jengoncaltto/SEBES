package br.uniriotec.prae.sebes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    List<Publicacao> findAllByServidor_Id(String idServidor);

    List<Publicacao> findAllByBolsa_Id(Integer idBolsa);

    List<Publicacao> findAllByOrderByDataPublicacaoDesc();
}
