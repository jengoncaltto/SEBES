package br.uniriotec.prae.sebes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Etapa;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, String> {

    List<Etapa> findAllByProcessoSeletivoId(String idProcessoSeletivo);
}
