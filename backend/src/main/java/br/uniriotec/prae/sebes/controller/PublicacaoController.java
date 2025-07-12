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

import br.uniriotec.prae.sebes.dto.PublicacaoDTO;
import br.uniriotec.prae.sebes.services.PublicacaoService;

@RestController
@RequestMapping("/api/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoService publicacaoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody PublicacaoDTO dto) {
        try {
            return publicacaoService.criar(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<PublicacaoDTO> listarTodas() {
        return publicacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            PublicacaoDTO dto = publicacaoService.buscarPorId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Integer id, @RequestBody PublicacaoDTO dto) {
        try {
            return publicacaoService.atualizarParcial(id, dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        try {
            return publicacaoService.deletar(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
