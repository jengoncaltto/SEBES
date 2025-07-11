package br.uniriotec.prae.sebes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.RespostaFormularioDTO;
import br.uniriotec.prae.sebes.entity.RespostaFormulario;
import br.uniriotec.prae.sebes.repository.ProcessoSeletivoRepository;
import br.uniriotec.prae.sebes.repository.RespostaFormularioRepository;
import br.uniriotec.prae.sebes.repository.UsuarioRepository;

@Service
public class RespostaFormularioService {

    @Autowired
    private RespostaFormularioRepository respostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProcessoSeletivoRepository processoRepository;

    public ResponseEntity<?> criar(RespostaFormularioDTO dto) {
        if (dto == null || dto.getConteudo() == null || dto.getConteudo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Conteúdo é obrigatório.");
        }

        if (dto.getIdUsuario() == null || !usuarioRepository.existsById(dto.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário informado não existe.");
        }

        if (dto.getIdProcessoSeletivo() == null || !processoRepository.existsById(dto.getIdProcessoSeletivo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Processo seletivo informado não existe.");
        }

        RespostaFormulario resposta = new RespostaFormulario();
        resposta.setId(UUID.randomUUID().toString());
        resposta.setConteudo(dto.getConteudo());
        resposta.setTipoForms(dto.getTipoForms());
        resposta.setIdUsuario(dto.getIdUsuario());
        resposta.setIdProcessoSeletivo(dto.getIdProcessoSeletivo());
        resposta.setStatus(dto.getStatus());
        resposta.setIdRespostaAssociada(dto.getIdRespostaAssociada());
        resposta.setDataEnvio(dto.getDataEnvio() != null ? dto.getDataEnvio() : LocalDateTime.now());

        RespostaFormulario salva = respostaRepository.save(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityToDTO(salva));
    }

    public List<RespostaFormularioDTO> listarTodas() {
        return respostaRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public RespostaFormularioDTO buscarPorId(String id) {
        RespostaFormulario resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada."));
        return entityToDTO(resposta);
    }

    public List<RespostaFormularioDTO> buscarPorStatus(String status) {
        return respostaRepository.findAllByStatus(status).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorTipoForms(String tipoForms) {
        return respostaRepository.findAllByTipoForms(tipoForms).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorIdUsuario(String idUsuario) {
        return respostaRepository.findAllByIdUsuario(idUsuario).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorIdProcessoSeletivo(String idProcessoSeletivo) {
        return respostaRepository.findAllByIdProcessoSeletivo(idProcessoSeletivo).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorDataEnvio(LocalDateTime dataEnvio) {
        return respostaRepository.findAllByDataEnvio(dataEnvio).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorIdRespostaAssociada(String idRespostaAssociada) {
        return respostaRepository.findAllByIdRespostaAssociada(idRespostaAssociada).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    // Novos métodos combinados
    public List<RespostaFormularioDTO> buscarPorTipoFormsEProcessoSeletivo(String tipoForms, String idProcessoSeletivo) {
        return respostaRepository.findAllByTipoFormsAndIdProcessoSeletivo(tipoForms, idProcessoSeletivo)
                .stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorTipoFormsEIdRespostaAssociada(String tipoForms, String idRespostaAssociada) {
        return respostaRepository.findAllByTipoFormsAndIdRespostaAssociada(tipoForms, idRespostaAssociada)
                .stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorTipoFormsProcessoSeletivoEStatus(String tipoForms, String idProcessoSeletivo, String status) {
        return respostaRepository.findAllByTipoFormsAndIdProcessoSeletivoAndStatus(tipoForms, idProcessoSeletivo, status)
                .stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorTipoFormsIdRespostaAssociadaEStatus(String tipoForms, String idRespostaAssociada, String status) {
        return respostaRepository.findAllByTipoFormsAndIdRespostaAssociadaAndStatus(tipoForms, idRespostaAssociada, status)
                .stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorTipoFormsEUsuario(String tipoForms, String idUsuario) {
        return respostaRepository.findAllByTipoFormsAndIdUsuario(tipoForms, idUsuario)
                .stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<RespostaFormularioDTO> buscarPorTipoFormsUsuarioEProcesso(String tipoForms, String idUsuario, String idProcessoSeletivo) {
        return respostaRepository.findAllByTipoFormsAndIdUsuarioAndIdProcessoSeletivo(tipoForms, idUsuario, idProcessoSeletivo)
                .stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public RespostaFormularioDTO atualizarParcial(String id, Map<String, Object> updates) {
        RespostaFormulario resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada."));

        if (updates.containsKey("status")) {
            resposta.setStatus((String) updates.get("status"));
        }
        if (updates.containsKey("conteudo")) {
            resposta.setConteudo((String) updates.get("conteudo"));
        }
        if (updates.containsKey("dataEnvio")) {
            try {
                String dataEnvioStr = (String) updates.get("dataEnvio");
                LocalDateTime dataEnvio = LocalDateTime.parse(dataEnvioStr);
                resposta.setDataEnvio(dataEnvio);
            } catch (Exception e) {
                throw new RuntimeException("Formato inválido para dataEnvio.");
            }
        }

        RespostaFormulario salva = respostaRepository.save(resposta);
        return entityToDTO(salva);
    }

    public ResponseEntity<?> deletar(String id) {
        if (!respostaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resposta não encontrada.");
        }
        respostaRepository.deleteById(id);
        return ResponseEntity.ok("Resposta deletada com sucesso.");
    }

    private RespostaFormularioDTO entityToDTO(RespostaFormulario resposta) {
        RespostaFormularioDTO dto = new RespostaFormularioDTO();
        dto.setId(resposta.getId());
        dto.setDataEnvio(resposta.getDataEnvio());
        dto.setTipoForms(resposta.getTipoForms());
        dto.setConteudo(resposta.getConteudo());
        dto.setStatus(resposta.getStatus());
        dto.setIdRespostaAssociada(resposta.getIdRespostaAssociada());
        dto.setIdUsuario(resposta.getIdUsuario());
        dto.setIdProcessoSeletivo(resposta.getIdProcessoSeletivo());
        return dto;
    }
}
