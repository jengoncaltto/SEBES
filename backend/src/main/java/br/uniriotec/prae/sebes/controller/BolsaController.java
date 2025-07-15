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

import br.uniriotec.prae.sebes.dto.BolsaDTO;
import br.uniriotec.prae.sebes.services.BolsaService;

@RestController
@RequestMapping("/api/bolsas")
public class BolsaController {

    @Autowired
    private BolsaService bolsaService;

    /* POST */
    @PostMapping
    public ResponseEntity<?> cadastrarBolsa(@RequestBody BolsaDTO dto) {
        try {
            BolsaDTO salva = bolsaService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /* GET */
    @GetMapping
    public ResponseEntity<List<BolsaDTO>> listarTodas() {
        return ResponseEntity.ok(bolsaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            BolsaDTO dto = bolsaService.buscarPorId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /* PATCH */
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Integer id, @RequestBody BolsaDTO dto) {
        try {
            BolsaDTO atualizado = bolsaService.atualizarParcial(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        try {
            bolsaService.deletar(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
