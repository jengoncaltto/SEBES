package com.example.sebes.Repositorio;
import com.example.sebes.Entity.Inscricao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer>{
    //Herdas os crud
}
