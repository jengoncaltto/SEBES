package br.uniriotec.prae.sebes.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.RespostaFormulario;
import br.uniriotec.prae.sebes.Repositorio.ProcessoSeletivoRepository;
import br.uniriotec.prae.sebes.Repositorio.RespostaFormularioRepository;
import br.uniriotec.prae.sebes.Repositorio.UsuarioRepository;

@RestController
@RequestMapping("/respostas")
public class RespostaFormularioController {

    @Autowired
    private RespostaFormularioRepository respostaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProcessoSeletivoRepository processoRepository;

    /* POST */
    
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody RespostaFormulario resposta) {
        // Validação de campos obrigatórios
        if (resposta.getConteudo() == null || resposta.getConteudo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Conteúdo é obrigatório.");
        }

        if (resposta.getIdUsuario() == null || resposta.getIdUsuario().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ID do usuário é obrigatório.");
        }

        if (resposta.getIdProcessoSeletivo() == null || resposta.getIdProcessoSeletivo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ID do processo seletivo é obrigatório.");
        }

        // Verifica se o usuário existe
        if (!usuarioRepository.existsById(resposta.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário informado não existe.");
        }

        // Verifica se o processo seletivo existe
        if (!processoRepository.existsById(resposta.getIdProcessoSeletivo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Processo seletivo informado não existe.");
        }

        // Define ID único
        resposta.setId(UUID.randomUUID().toString());

        // Define data de envio somente se não foi informada
        if (resposta.getDataEnvio() == null) {
            resposta.setDataEnvio(LocalDateTime.now());
        }

        RespostaFormulario salva = respostaRepository.save(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    
    /* GET */    
    
    @GetMapping
    public List<RespostaFormulario> listarTodas() {
        return respostaRepository.findAll();
    }

    
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

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Optional<RespostaFormulario> optResposta = respostaRepository.findById(id);

        if (optResposta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RespostaFormulario com id " + id + " não encontrada.");
        }

        RespostaFormulario resposta = optResposta.get();

        if (updates.containsKey("status")) {
            resposta.setStatus((String) updates.get("status"));
        }
        if (updates.containsKey("conteudo")) {
            resposta.setConteudo((String) updates.get("conteudo"));
        }
        if (updates.containsKey("dataEnvio")) {
            try {
                // Supondo que dataEnvio virá no formato ISO 8601, ex: "2025-06-28T15:00:00"
                String dataEnvioStr = (String) updates.get("dataEnvio");
                LocalDateTime dataEnvio = LocalDateTime.parse(dataEnvioStr);
                resposta.setDataEnvio(dataEnvio);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Formato inválido para dataEnvio.");
            }
        }

        respostaRepository.save(resposta);

        return ResponseEntity.ok(resposta);
    }
    
    /* DELETE */
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarResposta(@PathVariable String id) {
        Optional<RespostaFormulario> respostaOpt = respostaRepository.findById(id);

        if (!respostaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resposta não encontrada.");
        }

        respostaRepository.deleteById(id);
        return ResponseEntity.ok("Resposta deletada com sucesso.");
    }


}
