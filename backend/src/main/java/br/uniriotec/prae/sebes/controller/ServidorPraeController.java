package br.uniriotec.prae.sebes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.dto.ServidorPraeDTO;
import br.uniriotec.prae.sebes.services.ServidorPraeService;

@RestController
@RequestMapping("/api/servidores")
public class ServidorPraeController {

    @Autowired
    private ServidorPraeService servidorPraeService;
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ServidorPraeDTO dto) {
        return servidorPraeService.criar(dto);
    }

    @GetMapping
    public List<ServidorPraeDTO> listarTodos() {
        return servidorPraeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            ServidorPraeDTO servidor = servidorPraeService.buscarPorId(id);
            return ResponseEntity.ok(servidor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return servidorPraeService.atualizarParcial(id, updates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        return servidorPraeService.deletar(id);
    }
}
