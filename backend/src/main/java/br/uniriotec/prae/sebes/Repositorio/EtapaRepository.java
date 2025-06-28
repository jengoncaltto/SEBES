package br.uniriotec.prae.sebes.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.Entity.Etapa;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Integer>{
}
