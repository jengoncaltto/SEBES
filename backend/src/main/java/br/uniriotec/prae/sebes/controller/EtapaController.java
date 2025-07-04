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

import br.uniriotec.prae.sebes.dto.EtapaDTO;
import br.uniriotec.prae.sebes.services.EtapaService;

@RestController
@RequestMapping("/etapas")
public class EtapaController {

    @Autowired
    private EtapaService etapaService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody EtapaDTO dto) {
        try {
            EtapaDTO criada = etapaService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(criada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            EtapaDTO etapa = etapaService.buscarPorId(id);
            return ResponseEntity.ok(etapa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody EtapaDTO dto) {
        try {
            EtapaDTO atualizada = etapaService.atualizarParcial(id, dto);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        try {
            etapaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/processo/{idProcesso}")
    public ResponseEntity<List<EtapaDTO>> listarPorProcesso(@PathVariable String idProcesso) {
        List<EtapaDTO> etapas = etapaService.listarTodasDoProcesso(idProcesso);
        return ResponseEntity.ok(etapas);
    }
}
