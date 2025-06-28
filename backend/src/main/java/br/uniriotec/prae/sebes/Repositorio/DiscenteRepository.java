package br.uniriotec.prae.sebes.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.Entity.Discente;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, String> {
}
