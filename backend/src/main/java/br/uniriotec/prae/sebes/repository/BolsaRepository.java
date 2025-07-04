package br.uniriotec.prae.sebes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Bolsa;

@Repository
public interface BolsaRepository extends JpaRepository<Bolsa, Integer> {
}