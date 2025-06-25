package br.uniriotec.prae.sebes.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uniriotec.prae.sebes.Entity.Bolsa;

public interface BolsaRepository extends JpaRepository<Bolsa, Integer> {

    List<Bolsa> findByTipo(String tipo);

    // Usa exatamente o nome dos atributos da entidade Bolsa
    List<Bolsa> findByIdBolsaAndTipo(Integer idBolsa, String tipo);

    List<Bolsa> findByIdBolsa(Integer idBolsa);
}