package br.uniriotec.prae.sebes.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.DiscenteDTO;
import br.uniriotec.prae.sebes.dto.UsuarioDTO;
import br.uniriotec.prae.sebes.entity.Discente;
import br.uniriotec.prae.sebes.entity.Usuario;
import br.uniriotec.prae.sebes.repository.DiscenteRepository;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Object criar(DiscenteDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Dados do discente são obrigatórios.");
        }
        if (dto.getIdUsuario() == null || dto.getIdUsuario().isBlank()) {
            return ResponseEntity.badRequest().body("ID do usuário é obrigatório.");
        }
        if (dto.getMatricula() == null || dto.getMatricula().isBlank()) {
            return ResponseEntity.badRequest().body("Matrícula é obrigatória.");
        }
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório.");
        }
        if (dto.getTelefone() == null || dto.getTelefone().isBlank()) {
            return ResponseEntity.badRequest().body("Telefone é obrigatório.");
        }
        if (dto.getTelefone().length()>15) {
            return ResponseEntity.badRequest().body("Telefone inválido");
        }

        if (!usuarioService.isUsuarioCadastrado(dto.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
        }

        Discente discente = new Discente();
        discente.setId(UUID.randomUUID().toString());
        discente.setIdUsuario(dto.getIdUsuario());
        discente.setMatricula(dto.getMatricula());
        discente.setNome(dto.getNome());
        discente.setNomeSocial(dto.getNomeSocial());
        discente.setTelefone(dto.getTelefone());

        Discente salvo = discenteRepository.save(discente);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityToDTO(salvo));
    }

    public List<DiscenteDTO> listarTodos() {
        return discenteRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public DiscenteDTO buscarPorId(String id) {
        return discenteRepository.findById(id)
                .map(this::entityToDTO)
                .orElseThrow(() -> new RuntimeException("Discente não encontrado."));
    }

    public UsuarioDTO obterUsuario(String idUsuario) {
        return usuarioService.buscarPorId(idUsuario);
    }

    public ResponseEntity<?> atualizarParcial(String id, Map<String, Object> updates) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente não encontrado."));

        if (updates.containsKey("matricula")) {
            discente.setMatricula((String) updates.get("matricula"));
        }
        if (updates.containsKey("nome")) {
            discente.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("nomeSocial")) {
            discente.setNomeSocial((String) updates.get("nomeSocial"));
        }
        if (updates.containsKey("telefone")) {
            discente.setTelefone((String) updates.get("telefone"));
        }

        Discente atualizado = discenteRepository.save(discente);
        return ResponseEntity.ok(entityToDTO(atualizado));
    }

    public ResponseEntity<?> deletar(String id) {
        if (!discenteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Discente não encontrado.");
        }
        discenteRepository.deleteById(id);
        return ResponseEntity.ok("Discente removido com sucesso.");
    }

    private DiscenteDTO entityToDTO(Discente discente) {
        DiscenteDTO dto = new DiscenteDTO();

        dto.setId(discente.getId());
        dto.setIdUsuario(discente.getIdUsuario());
        dto.setNome(discente.getNome());
        dto.setNomeSocial(discente.getNomeSocial());
        dto.setMatricula(discente.getMatricula());
        dto.setTelefone(discente.getTelefone());

        Usuario usuario = usuarioService.buscarEntityPorId(discente.getIdUsuario());
        if (usuario != null) {
            dto.setNomeUsuario(usuario.getNomeUsuario());
            dto.setEmail(usuario.getEmail());
            dto.setEmailRecuperacao(usuario.getEmailRecuperacao());
        }

        return dto;
    }
}
