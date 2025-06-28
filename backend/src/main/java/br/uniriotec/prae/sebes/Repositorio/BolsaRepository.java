package br.uniriotec.prae.sebes.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.Entity.Bolsa;

@Repository
public interface BolsaRepository extends JpaRepository<Bolsa, Integer> {
}