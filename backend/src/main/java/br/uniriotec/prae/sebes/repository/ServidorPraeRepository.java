package br.uniriotec.prae.sebes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Discente;
import br.uniriotec.prae.sebes.entity.ServidorPrae;

@Repository
public interface ServidorPraeRepository extends JpaRepository<ServidorPrae, String> {
	
    Optional<Discente> findByIdUsuario(String idUsuario);

}
