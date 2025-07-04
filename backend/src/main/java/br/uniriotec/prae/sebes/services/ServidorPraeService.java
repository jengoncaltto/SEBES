package br.uniriotec.prae.sebes.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.ServidorPraeDTO;
import br.uniriotec.prae.sebes.dto.UsuarioDTO;
import br.uniriotec.prae.sebes.entity.ServidorPrae;
import br.uniriotec.prae.sebes.entity.Usuario;
import br.uniriotec.prae.sebes.repository.ServidorPraeRepository;

@Service
public class ServidorPraeService {

    @Autowired
    private ServidorPraeRepository servidorRepository;

    @Autowired
    private UsuarioService usuarioService;

    public ResponseEntity<?> criar(ServidorPraeDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Dados do servidor são obrigatórios.");
        }
        if (dto.getIdUsuario() == null || dto.getIdUsuario().isBlank()) {
            return ResponseEntity.badRequest().body("ID do usuário é obrigatório.");
        }
        if (dto.getCargo() == null || dto.getCargo().isBlank()) {
            return ResponseEntity.badRequest().body("Cargo é obrigatório.");
        }
        if (dto.getSetor() == null || dto.getSetor().isBlank()) {
            return ResponseEntity.badRequest().body("Setor é obrigatório.");
        }
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório.");
        }
        if (dto.getTelefone() == null || dto.getTelefone().isBlank()) {
            return ResponseEntity.badRequest().body("Telefone é obrigatório.");
        }

        Usuario usuario = usuarioService.buscarEntityPorId(dto.getIdUsuario());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
        }

        if (servidorRepository.existsById(dto.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Servidor já cadastrado com esse usuário.");
        }

        ServidorPrae servidor = new ServidorPrae();
        servidor.setId(dto.getIdUsuario()); // id do usuário é id do servidor
        servidor.setUsuario(usuario);
        servidor.setCargo(dto.getCargo());
        servidor.setSetor(dto.getSetor());
        servidor.setNome(dto.getNome());
        servidor.setNomeSocial(dto.getNomeSocial());
        servidor.setTelefone(dto.getTelefone());

        ServidorPrae salvo = servidorRepository.save(servidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityToDTO(salvo));
    }

    public List<ServidorPraeDTO> listarTodos() {
        return servidorRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public ServidorPraeDTO buscarPorId(String id) {
        return servidorRepository.findById(id)
                .map(this::entityToDTO)
                .orElseThrow(() -> new RuntimeException("Servidor não encontrado."));
    }

    public UsuarioDTO obterUsuario(String idUsuario) {
        return usuarioService.buscarPorId(idUsuario);
    }

    public ResponseEntity<?> atualizarParcial(String id, Map<String, Object> updates) {
        ServidorPrae servidor = servidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servidor não encontrado."));

        if (updates.containsKey("cargo")) {
            servidor.setCargo((String) updates.get("cargo"));
        }
        if (updates.containsKey("setor")) {
            servidor.setSetor((String) updates.get("setor"));
        }
        if (updates.containsKey("nome")) {
            servidor.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("nomeSocial")) {
            servidor.setNomeSocial((String) updates.get("nomeSocial"));
        }
        if (updates.containsKey("telefone")) {
            servidor.setTelefone((String) updates.get("telefone"));
        }

        ServidorPrae atualizado = servidorRepository.save(servidor);
        return ResponseEntity.ok(entityToDTO(atualizado));
    }

    public ResponseEntity<?> deletar(String id) {
        if (!servidorRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado.");
        }
        servidorRepository.deleteById(id);
        return ResponseEntity.ok("Servidor removido com sucesso.");
    }

    private ServidorPraeDTO entityToDTO(ServidorPrae servidor) {
        ServidorPraeDTO dto = new ServidorPraeDTO();

        dto.setId(servidor.getId());
        dto.setIdUsuario(servidor.getUsuario().getId());
        dto.setNome(servidor.getNome());
        dto.setNomeSocial(servidor.getNomeSocial());
        dto.setCargo(servidor.getCargo());
        dto.setSetor(servidor.getSetor());
        dto.setTelefone(servidor.getTelefone());

        Usuario u = servidor.getUsuario();
        if (u != null) {
            dto.setEmail(u.getEmail());
            dto.setEmailRecuperacao(u.getEmailRecuperacao());
        }

        return dto;
    }
}
