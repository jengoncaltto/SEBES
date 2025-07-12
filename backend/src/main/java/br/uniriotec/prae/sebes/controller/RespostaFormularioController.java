package br.uniriotec.prae.sebes.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.uniriotec.prae.sebes.dto.RespostaFormularioDTO;
import br.uniriotec.prae.sebes.services.RespostaFormularioService;

@RestController
@RequestMapping("/api/respostas")
public class RespostaFormularioController {

    @Autowired
    private RespostaFormularioService respostaService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody RespostaFormularioDTO dto) {
        return respostaService.criar(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            RespostaFormularioDTO dto = respostaService.buscarPorId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public List<RespostaFormularioDTO> buscarPorStatus(@PathVariable String status) {
        return respostaService.buscarPorStatus(status);
    }

    @GetMapping("/tipo/{tipoForms}")
    public List<RespostaFormularioDTO> buscarPorTipoForms(@PathVariable String tipoForms) {
        return respostaService.buscarPorTipoForms(tipoForms);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<RespostaFormularioDTO> buscarPorIdUsuario(@PathVariable String idUsuario) {
        return respostaService.buscarPorIdUsuario(idUsuario);
    }

    @GetMapping("/processo/{idProcessoSeletivo}")
    public List<RespostaFormularioDTO> buscarPorIdProcessoSeletivo(@PathVariable String idProcessoSeletivo) {
        return respostaService.buscarPorIdProcessoSeletivo(idProcessoSeletivo);
    }

    @GetMapping("/data/{dataEnvio}")
    public List<RespostaFormularioDTO> buscarPorDataEnvio(@PathVariable String dataEnvio) {
        return respostaService.buscarPorDataEnvio(LocalDateTime.parse(dataEnvio));
    }

    @GetMapping("/resposta-associada/{idRespostaAssociada}")
    public List<RespostaFormularioDTO> buscarPorIdRespostaAssociada(@PathVariable String idRespostaAssociada) {
        return respostaService.buscarPorIdRespostaAssociada(idRespostaAssociada);
    }

    // Novos endpoints para buscas compostas:

    @GetMapping("/tipo-processo")
    public List<RespostaFormularioDTO> buscarPorTipoFormsEProcessoSeletivo(
            @RequestParam String tipoForms,
            @RequestParam String idProcessoSeletivo) {
        return respostaService.buscarPorTipoFormsEProcessoSeletivo(tipoForms, idProcessoSeletivo);
    }

    @GetMapping("/tipo-resposta-associada")
    public List<RespostaFormularioDTO> buscarPorTipoFormsEIdRespostaAssociada(
            @RequestParam String tipoForms,
            @RequestParam String idRespostaAssociada) {
        return respostaService.buscarPorTipoFormsEIdRespostaAssociada(tipoForms, idRespostaAssociada);
    }

    @GetMapping("/tipo-processo-status")
    public List<RespostaFormularioDTO> buscarPorTipoFormsProcessoSeletivoEStatus(
            @RequestParam String tipoForms,
            @RequestParam String idProcessoSeletivo,
            @RequestParam String status) {
        return respostaService.buscarPorTipoFormsProcessoSeletivoEStatus(tipoForms, idProcessoSeletivo, status);
    }

    @GetMapping("/tipo-resposta-associada-status")
    public List<RespostaFormularioDTO> buscarPorTipoFormsIdRespostaAssociadaEStatus(
            @RequestParam String tipoForms,
            @RequestParam String idRespostaAssociada,
            @RequestParam String status) {
        return respostaService.buscarPorTipoFormsIdRespostaAssociadaEStatus(tipoForms, idRespostaAssociada, status);
    }

    @GetMapping("/tipo-usuario")
    public List<RespostaFormularioDTO> buscarPorTipoFormsEUsuario(
            @RequestParam String tipoForms,
            @RequestParam String idUsuario) {
        return respostaService.buscarPorTipoFormsEUsuario(tipoForms, idUsuario);
    }

    @GetMapping("/tipo-usuario-processo")
    public List<RespostaFormularioDTO> buscarPorTipoFormsUsuarioEProcesso(
            @RequestParam String tipoForms,
            @RequestParam String idUsuario,
            @RequestParam String idProcessoSeletivo) {
        return respostaService.buscarPorTipoFormsUsuarioEProcesso(tipoForms, idUsuario, idProcessoSeletivo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        try {
            RespostaFormularioDTO atualizada = respostaService.atualizarParcial(id, updates);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        try {
            return respostaService.deletar(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
