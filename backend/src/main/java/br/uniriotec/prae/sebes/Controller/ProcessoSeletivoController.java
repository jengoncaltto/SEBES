package br.uniriotec.prae.sebes.Controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import br.uniriotec.prae.sebes.Entity.Bolsa;
import br.uniriotec.prae.sebes.Entity.ProcessoSeletivo;
import br.uniriotec.prae.sebes.Repositorio.BolsaRepository;
import br.uniriotec.prae.sebes.Repositorio.ProcessoSeletivoRepository;
import br.uniriotec.prae.sebes.dto.ProcessoDTO;


@RestController
@RequestMapping("/processos")
public class ProcessoSeletivoController {
    
    @Autowired
    ProcessoSeletivoRepository processoRepository;
    
    @Autowired
    BolsaRepository bolsaRepository;
    
    /* POST */
    
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ProcessoDTO request) {
        // Verifica se o ID da bolsa foi informado
        if (request.getIdBolsa() == null) {
            return ResponseEntity.badRequest().body("O ID da bolsa deve ser informado.");
        }

        // Busca a bolsa no banco
        Optional<Bolsa> bolsaOpt = bolsaRepository.findById(request.getIdBolsa());
        if (bolsaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Bolsa informada não existe.");
        }

        // Cria o processo seletivo a partir do request
        ProcessoSeletivo processo = new ProcessoSeletivo();
        processo.setDataInicio(request.getDataInicio());
        processo.setDataEncerramento(request.getDataEncerramento());
        processo.setStatus(request.getStatus());
        processo.setBolsa(bolsaOpt.get());

        // Salva o processo
        ProcessoSeletivo salvo = processoRepository.save(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    
    /* GET */

    @GetMapping
    public ResponseEntity<List<ProcessoSeletivo>> listarTodos() {
        return ResponseEntity.ok(processoRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<ProcessoSeletivo> result = processoRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Processo seletivo não encontrado.");
        }
    }
    

    // Buscar por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProcessoSeletivo>> buscarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(processoRepository.findAllByStatus(status));
    }

    // Buscar por idBolsa
    @GetMapping("/bolsa/{idBolsa}")
    public ResponseEntity<List<ProcessoSeletivo>> buscarPorBolsa(@PathVariable Integer idBolsa) {
        return ResponseEntity.ok(processoRepository.findAllByBolsa_Id(idBolsa));
    }
    
    /* PATCH */

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Optional<ProcessoSeletivo> processoOpt = processoRepository.findById(id);
        if (!processoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Processo seletivo com id " + id + " não encontrado.");
        }

        ProcessoSeletivo processo = processoOpt.get();

        // Atualiza dataInicio se presente
        if (updates.containsKey("dataInicio")) {
            try {
                String dataInicioStr = (String) updates.get("dataInicio");
                LocalDateTime dataInicio = LocalDateTime.parse(dataInicioStr);
                processo.setDataInicio(dataInicio);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Formato inválido para dataInicio. Use 'yyyy-[m]m-[d]d hh:mm:ss[.f...]'");
            }
        }

        // Atualiza dataEncerramento se presente
        if (updates.containsKey("dataEncerramento")) {
            try {
                String dataEncerramentoStr = (String) updates.get("dataEncerramento");
                LocalDateTime dataEncerramento = LocalDateTime.parse(dataEncerramentoStr);
                processo.setDataEncerramento(dataEncerramento);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Formato inválido para dataEncerramento. Use 'yyyy-[m]m-[d]d hh:mm:ss[.f...]'");
            }
        }

        // Atualiza status se presente
        if (updates.containsKey("status")) {
            String status = (String) updates.get("status");
            processo.setStatus(status);
        }

        processoRepository.save(processo);
        return ResponseEntity.ok(processo);
    }

    
    /* DELETE */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProcesso(@PathVariable String id) {
        if(processoRepository.existsById(id)) {
        	processoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Processo seletivo não encontrada.");
    }
    
}
