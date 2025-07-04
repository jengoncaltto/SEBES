package br.uniriotec.prae.sebes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.PublicacaoDTO;
import br.uniriotec.prae.sebes.entity.Publicacao;
import br.uniriotec.prae.sebes.entity.Usuario;
import br.uniriotec.prae.sebes.repository.PublicacaoRepository;
import br.uniriotec.prae.sebes.repository.UsuarioRepository;

@Service
public class PublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar nova publicação
    public ResponseEntity<?> criar(PublicacaoDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Dados da publicação são obrigatórios.");
        }

        if (dto.getConteudo() == null || dto.getConteudo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Conteúdo é obrigatório.");
        }

        if (dto.getIdUsuario() == null || dto.getIdUsuario().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ID do usuário é obrigatório.");
        }

        // Verificar se usuário existe
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário informado não existe.");
        }

        Publicacao pub = new Publicacao();
        pub.setConteudo(dto.getConteudo());
        pub.setUsuario(usuario);
        pub.setDataPublicacao(LocalDateTime.now());
        pub.setDataAtualizacao(LocalDateTime.now());

        Publicacao salva = publicacaoRepository.save(pub);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityToDTO(salva));
    }

    // Listar todas
    public List<PublicacaoDTO> listarTodas() {
        return publicacaoRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    // Buscar por ID
    public PublicacaoDTO buscarPorId(Integer id) {
        Publicacao pub = publicacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada."));
        return entityToDTO(pub);
    }

    // Atualizar parcialmente
    public ResponseEntity<?> atualizarParcial(Integer id, PublicacaoDTO dto) {
        Publicacao pub = publicacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada."));

        boolean mudou = false;

        if (dto.getConteudo() != null && !dto.getConteudo().equals(pub.getConteudo())) {
            pub.setConteudo(dto.getConteudo());
            mudou = true;
        }

        if (dto.getIdUsuario() != null && !dto.getIdUsuario().equals(pub.getUsuario().getId())) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElse(null);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário informado não existe.");
            }
            pub.setUsuario(usuario);
            mudou = true;
        }

        if (mudou) {
            pub.setDataAtualizacao(LocalDateTime.now());
            Publicacao atualizado = publicacaoRepository.save(pub);
            return ResponseEntity.ok(entityToDTO(atualizado));
        } else {
            return ResponseEntity.ok(entityToDTO(pub));
        }
    }

    // Deletar
    public ResponseEntity<?> deletar(Integer id) {
        if (!publicacaoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicação não encontrada.");
        }
        publicacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversão entity -> DTO
    private PublicacaoDTO entityToDTO(Publicacao pub) {
        PublicacaoDTO dto = new PublicacaoDTO();
        dto.setId(pub.getId());
        dto.setConteudo(pub.getConteudo());
        dto.setDataPublicacao(pub.getDataPublicacao());
        dto.setIdUsuario(pub.getUsuario() != null ? pub.getUsuario().getId() : null);
        return dto;
    }
}
