package br.uniriotec.prae.sebes.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uniriotec.prae.sebes.Entity.Etapa;

public interface EtapaRepository extends JpaRepository<Etapa, Integer>{
    //Pegas os cruds
}
