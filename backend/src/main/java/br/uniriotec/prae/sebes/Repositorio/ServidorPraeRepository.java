package br.uniriotec.prae.sebes.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uniriotec.prae.sebes.Entity.ServidorPrae;

public interface ServidorPraeRepository extends JpaRepository<ServidorPrae, Integer>{
    
}
