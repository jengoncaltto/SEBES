package br.uniriotec.prae.sebes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Discente;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, String> {

    Optional<Discente> findByIdUsuario(String idUsuario);

}
