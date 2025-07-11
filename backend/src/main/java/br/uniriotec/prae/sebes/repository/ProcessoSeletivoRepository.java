package br.uniriotec.prae.sebes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.ProcessoSeletivo;

@Repository
public interface ProcessoSeletivoRepository extends JpaRepository<ProcessoSeletivo, String> {

    List<ProcessoSeletivo> findAllByStatus(String status);

    List<ProcessoSeletivo> findAllByBolsa_Id(Integer idBolsa);

}
