package br.uniriotec.prae.sebes.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.RespostaFormulario;
import br.uniriotec.prae.sebes.Repositorio.RespostaFormularioRepository;

@RestController
@RequestMapping("/respostas")
public class RespostaFormularioController {

    @Autowired
    private RespostaFormularioRepository respostaRepository;

    /* GET */
    
    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<RespostaFormulario> result = respostaRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Resposta com id " + id + " não encontrada.");
        }
    }

    // Buscar por status
    @GetMapping("/status/{status}")
    public List<RespostaFormulario> buscarPorStatus(@PathVariable String status) {
        return respostaRepository.findAllByStatus(status);
    }

    // Buscar por tipoForms
    @GetMapping("/tipo/{tipoForms}")
    public List<RespostaFormulario> buscarPorTipoForms(@PathVariable String tipoForms) {
        return respostaRepository.findAllByTipoForms(tipoForms);
    }

    // Buscar por idUsuario
    @GetMapping("/usuario/{idUsuario}")
    public List<RespostaFormulario> buscarPorIdUsuario(@PathVariable String idUsuario) {
        return respostaRepository.findAllByIdUsuario(idUsuario);
    }

    // Buscar por idProcessoSeletivo
    @GetMapping("/processo/{idProcessoSeletivo}")
    public List<RespostaFormulario> buscarPorIdProcessoSeletivo(@PathVariable String idProcessoSeletivo) {
        return respostaRepository.findAllByIdProcessoSeletivo(idProcessoSeletivo);
    }

    // Buscar por dataEnvio (exata)
    @GetMapping("/data/{dataEnvio}")
    public List<RespostaFormulario> buscarPorDataEnvio(@PathVariable String dataEnvio) {
        return respostaRepository.findAllByDataEnvio(LocalDateTime.parse(dataEnvio));
    }
    

    // Buscar por idRespostaAssociada
    @GetMapping("/processo/{idRespostaAssociada}")
    public List<RespostaFormulario> buscarPorIdRespostaAssociadas(@PathVariable String idRespostaAssociada) {
        return respostaRepository.findAllByIdRespostaAssociada(idRespostaAssociada);
    }

    /* PATCH */

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        Optional<RespostaFormulario> result = respostaRepository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Resposta com id " + id + " não encontrada.");
        }

        RespostaFormulario resposta = result.get();
        String novoStatus = body.get("status");

        if (novoStatus == null || novoStatus.isBlank()) {
            return ResponseEntity.badRequest().body("Campo 'status' é obrigatório.");
        }

        resposta.setStatus(novoStatus);
        respostaRepository.save(resposta);

        return ResponseEntity.ok("Status atualizado com sucesso.");
    }


}
