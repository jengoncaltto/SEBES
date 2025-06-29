package br.uniriotec.prae.sebes.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.Bolsa;
import br.uniriotec.prae.sebes.Repositorio.BolsaRepository;

@RestController
@RequestMapping("/bolsas")
public class BolsaController {

    @Autowired
    private BolsaRepository bolsaRepository;

    /* POST */
    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarBolsa(@RequestBody Bolsa request) {
        if (request.getNome() == null || request.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("O nome da bolsa é obrigatório.");
        }

        if (request.getValor() == null || request.getValor().doubleValue() <= 0) {
            return ResponseEntity.badRequest().body("O valor da bolsa deve ser maior que zero.");
        }

        if (request.getPeriodo() == null || request.getPeriodo() <= 0) {
            return ResponseEntity.badRequest().body("O período da bolsa deve ser informado e maior que zero.");
        }

        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            return ResponseEntity.badRequest().body("A descrição da bolsa é obrigatória.");
        }

        Bolsa bolsaSalva = bolsaRepository.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(bolsaSalva);
    }

    
    /* GET */
    
    @GetMapping
    public List<Bolsa> listarTodas() {
        return bolsaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Optional<Bolsa> result = bolsaRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Bolsa não encontrada.");
        }
    }


    /* PATCH */

    // Editar apenas campos específicos
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Optional<Bolsa> bolsaOpt = bolsaRepository.findById(id);
        if (!bolsaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Bolsa não encontrada.");
        }

        Bolsa bolsa = bolsaOpt.get();

        updates.forEach((chave, valor) -> {
            switch (chave) {
                case "nome":
                    bolsa.setNome((String) valor);
                    break;
                case "descricao":
                    bolsa.setDescricao((String) valor);
                    break;
                case "valor":
                    // pode precisar converter para BigDecimal
                    if (valor instanceof Number) {
                        bolsa.setValor(BigDecimal.valueOf(((Number) valor).doubleValue()));
                    } else if (valor instanceof String) {
                        bolsa.setValor(new BigDecimal((String) valor));
                    }
                    break;
                case "periodo":
                    if (valor instanceof Number) {
                        bolsa.setPeriodo(((Number) valor).intValue());
                    }
                    break;
                // adiciona outros campos conforme necessário
            }
        });

        Bolsa bolsaAtualizada = bolsaRepository.save(bolsa);
        return ResponseEntity.ok(bolsaAtualizada);
    }

}
