package br.uniriotec.prae.sebes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // Buscar usuário pelo nome de usuário exato
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
    
    // Buscar usuário pelo tipo
    List<Usuario> findByTipo(String tipo);

    // Verificar existência por email
    boolean existsByEmail(String email);

    // Buscar usuário pelo email
    Optional<Usuario> findByEmail(String email);

    // Buscar usuário pelo email de recuperação
    Optional<Usuario> findByEmailRecuperacao(String emailRecuperacao);

    // Verificar existência por nome de usuário
    boolean existsByNomeUsuario(String nomeUsuario);
}
