package br.uniriotec.prae.sebes.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.UsuarioDTO;
import br.uniriotec.prae.sebes.entity.Usuario;
import br.uniriotec.prae.sebes.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar novo usuário
    public ResponseEntity<?> criar(UsuarioDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Dados do usuário são obrigatórios.");
        }

        if (dto.getNomeUsuario() == null || dto.getNomeUsuario().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome de usuário é obrigatório.");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email é obrigatório.");
        }

        if (dto.getEmailRecuperacao() == null || dto.getEmailRecuperacao().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email de recuperação é obrigatório.");
        }

        // Verificar se nomeUsuario já existe
        if (usuarioRepository.existsByNomeUsuario(dto.getNomeUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já existe.");
        }

        // Verificar se email já existe
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID().toString());
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setEmailRecuperacao(dto.getEmailRecuperacao());

        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityToDTO(salvo));
    }

    // Buscar por ID
    public UsuarioDTO buscarPorId(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return entityToDTO(usuario);
    }

    public Usuario buscarEntityPorId(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return usuario;
    }
    
	public Usuario buscaPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		return usuario;
	}
    
	public boolean isEmailCadastrado(String email) {
        return usuarioRepository.existsByEmail(email);
	}
	
	public boolean isUsuarioCadastrado(String idUsuario) {
		return usuarioRepository.existsById(idUsuario);
	}

    // Listar todos usuários
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    // Atualizar usuário (parcialmente)
    public ResponseEntity<?> atualizarParcial(String id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (dto.getNomeUsuario() != null && !dto.getNomeUsuario().equals(usuario.getNomeUsuario())) {
            if (usuarioRepository.existsByNomeUsuario(dto.getNomeUsuario())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já existe.");
            }
            usuario.setNomeUsuario(dto.getNomeUsuario());
        }

        if (dto.getEmail() != null && !dto.getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.existsByEmail(dto.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está em uso.");
            }
            usuario.setEmail(dto.getEmail());
        }

        if (dto.getEmailRecuperacao() != null) {
            usuario.setEmailRecuperacao(dto.getEmailRecuperacao());
        }

        Usuario atualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(entityToDTO(atualizado));
    }

    // Deletar usuário
    public ResponseEntity<?> deletar(String id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversão entity -> DTO
    private UsuarioDTO entityToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setEmailRecuperacao(usuario.getEmailRecuperacao());
        return dto;
    }
}
