package br.uniriotec.prae.sebes.controller;

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

import br.uniriotec.prae.sebes.dto.EtapaDTO;
import br.uniriotec.prae.sebes.entity.Etapa;
import br.uniriotec.prae.sebes.entity.ProcessoSeletivo;
import br.uniriotec.prae.sebes.repository.EtapaRepository;
import br.uniriotec.prae.sebes.repository.ProcessoSeletivoRepository;

@RestController
@RequestMapping("/etapas")
public class EtapaController {

    @Autowired
    EtapaRepository etapaRepository;

    @Autowired
    ProcessoSeletivoRepository processoRepository;

    /* POST */

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody EtapaDTO request) {
        // Valida ID do processo seletivo
        if (request.getIdProcessoSeletivo() == null || !processoRepository.existsById(request.getIdProcessoSeletivo())) {
            return ResponseEntity.badRequest().body("Processo seletivo inválido ou inexistente.");
        }

        // Verifica unicidade do tipoEtapa no processo
        List<Etapa> etapasExistentes = etapaRepository.findAllByProcessoSeletivoId(request.getIdProcessoSeletivo());
        boolean tipoJaExiste = etapasExistentes.stream()
                .anyMatch(e -> e.getTipoEtapa().equalsIgnoreCase(request.getTipoEtapa()));

        if (tipoJaExiste) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe uma etapa do tipo '" + request.getTipoEtapa() + "' para este processo seletivo.");
        }

        // Recupera o processo seletivo
        ProcessoSeletivo processo = processoRepository.findById(request.getIdProcessoSeletivo()).get();

        // Cria e salva a etapa
        Etapa novaEtapa = new Etapa();
        novaEtapa.setTipoEtapa(request.getTipoEtapa());
        novaEtapa.setDataInicio(request.getDataInicio());
        novaEtapa.setDataEncerramento(request.getDataEncerramento());
        novaEtapa.setStatus(request.getStatus());
        novaEtapa.setProcessoSeletivo(processo);

        Etapa salva = etapaRepository.save(novaEtapa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    // * GET */

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<Etapa> result = etapaRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Bolsa não encontrada.");
        }
    }

    /* PATCH */

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarEtapa(@PathVariable String id, @RequestBody Map<String, Object> body) {
        Optional<Etapa> result = etapaRepository.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etapa não encontrada.");
        }

        Etapa etapa = result.get();

        if (body.containsKey("dataInicio")) {
            try {
                String dataInicioStr = body.get("dataInicio").toString();
                LocalDateTime dataInicio = LocalDateTime.parse(dataInicioStr);
                etapa.setDataInicio(dataInicio);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Formato inválido para dataInicio. Use yyyy-MM-dd HH:mm:ss[.fffffffff]");
            }
        }

        if (body.containsKey("dataEncerramento")) {
            try {
                String dataEncerramentoStr = body.get("dataEncerramento").toString();
                LocalDateTime dataEncerramento = LocalDateTime.parse(dataEncerramentoStr);
                etapa.setDataEncerramento(dataEncerramento);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Formato inválido para dataEncerramento. Use yyyy-MM-dd HH:mm:ss[.fffffffff]");
            }
        }

        if (body.containsKey("status")) {
            etapa.setStatus(body.get("status").toString());
        }

        etapaRepository.save(etapa);
        return ResponseEntity.ok(etapa);
    }


    /* DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEtapa(@PathVariable String id) {
        if (etapaRepository.existsById(id)) {
            etapaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Etapa não encontrada.");
    }

}

// Criar (deve ser ligada a um processo)
// Editar (apenas datas e status)
// Excluir */
