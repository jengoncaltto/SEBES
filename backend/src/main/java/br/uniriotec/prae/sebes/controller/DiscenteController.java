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

import br.uniriotec.prae.sebes.dto.DiscenteDTO;
import br.uniriotec.prae.sebes.dto.UsuarioDTO;
import br.uniriotec.prae.sebes.services.DiscenteService;

@RestController
@RequestMapping("/api/discentes")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody DiscenteDTO dto) {
        return discenteService.criar(dto);
    }

    @GetMapping
    public List<DiscenteDTO> listarTodos() {
        return discenteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            DiscenteDTO discente = discenteService.buscarPorId(id);
            return ResponseEntity.ok(discente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/usuario")
    public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable String id) {
        try {
            DiscenteDTO discente = discenteService.buscarPorId(id);
            UsuarioDTO usuarioDTO = discenteService.obterUsuario(discente.getIdUsuario());
            return ResponseEntity.ok(usuarioDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return discenteService.atualizarParcial(id, updates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        return discenteService.deletar(id);
    }
}
