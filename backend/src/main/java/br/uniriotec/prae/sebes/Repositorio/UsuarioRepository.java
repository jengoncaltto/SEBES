package br.uniriotec.prae.sebes.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import br.uniriotec.prae.sebes.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>  {
    
}
