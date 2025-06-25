package br.uniriotec.prae.sebes.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import br.uniriotec.prae.sebes.Entity.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer>{
    //Herdas os crud
}
