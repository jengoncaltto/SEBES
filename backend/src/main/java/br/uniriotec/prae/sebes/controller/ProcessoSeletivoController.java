package br.uniriotec.prae.sebes.controller;

import java.util.List;

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

import br.uniriotec.prae.sebes.dto.ProcessoSeletivoDTO;
import br.uniriotec.prae.sebes.services.ProcessoSeletivoService;

@RestController
@RequestMapping("/api/processos")
public class ProcessoSeletivoController {

    @Autowired
    private ProcessoSeletivoService processoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ProcessoSeletivoDTO dto) {
        try {
            ProcessoSeletivoDTO criado = processoService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProcessoSeletivoDTO>> listarTodos() {
        return ResponseEntity.ok(processoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(processoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProcessoSeletivoDTO>> buscarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(processoService.buscarPorStatus(status));
    }

    @GetMapping("/bolsa/{idBolsa}")
    public ResponseEntity<List<ProcessoSeletivoDTO>> buscarPorBolsa(@PathVariable Integer idBolsa) {
        return ResponseEntity.ok(processoService.buscarPorBolsa(idBolsa));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody ProcessoSeletivoDTO dto) {
        try {
            ProcessoSeletivoDTO atualizado = processoService.atualizarParcial(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        try {
            processoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
