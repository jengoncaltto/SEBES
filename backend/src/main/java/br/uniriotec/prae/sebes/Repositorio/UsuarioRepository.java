package com.example.sebes.Repositorio;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sebes.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {
    
}
