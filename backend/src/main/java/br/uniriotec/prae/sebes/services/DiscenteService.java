package br.uniriotec.prae.sebes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.DiscenteDTO;
import br.uniriotec.prae.sebes.entity.Discente;
import br.uniriotec.prae.sebes.entity.Usuario;
import br.uniriotec.prae.sebes.repository.DiscenteRepository;
import br.uniriotec.prae.sebes.repository.UsuarioRepository;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<DiscenteDTO> listarTodos() {
        return discenteRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public DiscenteDTO buscarPorId(String id) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente não encontrado."));
        return entityToDTO(discente);
    }

    public DiscenteDTO cadastrar(DiscenteDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dados do discente são obrigatórios.");
        }
        if (dto.getId() == null || dto.getId().isBlank()) {
            throw new IllegalArgumentException("ID do usuário é obrigatório.");
        }
        if (dto.getCurso() == null || dto.getCurso().isBlank()) {
            throw new IllegalArgumentException("Curso é obrigatório.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID informado."));

        if (discenteRepository.existsById(dto.getId())) {
            throw new RuntimeException("Discente com esse ID já existe.");
        }

        Discente discente = new Discente();
        discente.setId(dto.getId());
        discente.setUsuario(usuario);
        discente.setCurso(dto.getCurso());

        Discente salvo = discenteRepository.save(discente);

        return entityToDTO(salvo);
    }

    public DiscenteDTO atualizarParcial(String id, DiscenteDTO dto) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente não encontrado."));

        if (dto == null) {
            throw new IllegalArgumentException("Dados do discente são obrigatórios para atualização.");
        }

        if (dto.getCurso() != null && !dto.getCurso().isBlank()) {
            discente.setCurso(dto.getCurso());
        }

        Usuario usuario = discente.getUsuario();
        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            usuario.setNome(dto.getNome());
        }
        if (dto.getNomeSocial() != null) {
            usuario.setNomeSocial(dto.getNomeSocial());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            usuario.setEmail(dto.getEmail());
        }
        if (dto.getEmailRecuperacao() != null) {
            usuario.setEmailRecuperacao(dto.getEmailRecuperacao());
        }
        if (dto.getTelefone() != null) {
            usuario.setTelefone(dto.getTelefone());
        }
        if (dto.getStatus() != null) {
            usuario.setStatus(dto.getStatus());
        }

        usuarioRepository.save(usuario);
        Discente atualizado = discenteRepository.save(discente);

        return entityToDTO(atualizado);
    }

    public void deletar(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID do discente é obrigatório para exclusão.");
        }
        if (!discenteRepository.existsById(id)) {
            throw new RuntimeException("Discente não encontrado para exclusão.");
        }
        discenteRepository.deleteById(id);
    }

    private DiscenteDTO entityToDTO(Discente discente) {
        DiscenteDTO dto = new DiscenteDTO();
        Usuario u = discente.getUsuario();

        dto.setId(discente.getId());
        dto.setCurso(discente.getCurso());

        if (u != null) {
            dto.setNome(u.getNome());
            dto.setNomeSocial(u.getNomeSocial());
            dto.setEmail(u.getEmail());
            dto.setEmailRecuperacao(u.getEmailRecuperacao());
            dto.setTelefone(u.getTelefone());
            dto.setStatus(u.getStatus());
        }

        return dto;
    }
}
