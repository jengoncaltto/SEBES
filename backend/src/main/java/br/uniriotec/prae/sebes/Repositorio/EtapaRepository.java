package com.example.sebes.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sebes.Entity.Etapa;

public interface EtapaRepository extends JpaRepository<Etapa, Integer>{
    //Pegas os cruds
}
