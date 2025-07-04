package br.uniriotec.prae.sebes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.ServidorPrae;

@Repository
public interface ServidorPraeRepository extends JpaRepository<ServidorPrae, String> {

    boolean existsByCargo(String cargo);

    boolean existsBySetor(String setor);
}
