package br.uniriotec.prae.sebes.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uniriotec.prae.sebes.Entity.ProcessoSeletivo;


public interface ProcessoSeletivoRepository extends JpaRepository<ProcessoSeletivo, Integer> {
     // Aqui você já herda métodos para CRUD, como save, findAll, findById, deleteById etc.
    
}
