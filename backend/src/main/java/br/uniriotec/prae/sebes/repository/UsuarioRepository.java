package br.uniriotec.prae.sebes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    boolean existsByEmail(String email);

    boolean existsByStatus(String status);

}
