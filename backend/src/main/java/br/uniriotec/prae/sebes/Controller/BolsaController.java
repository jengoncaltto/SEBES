package br.uniriotec.prae.sebes.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.uniriotec.prae.sebes.Entity.Bolsa;
import br.uniriotec.prae.sebes.Repositorio.BolsaRepository;

@RestController
@RequestMapping("/bolsa")
public class BolsaController {

    @Autowired
    private BolsaRepository b;

    // Criar nova bolsa
    @PostMapping
    public Bolsa cadastrarBolsa(@RequestBody Bolsa bolsa) {
        return b.save(bolsa);
    }

    // Buscar todas as bolsas
    @GetMapping
    public List<Bolsa> getBolsas() {
        return b.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Bolsa> buscarPorId(@PathVariable Integer id) {
        Optional<Bolsa> resultado = b.findById(id);

        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por ID e/ou tipo
    @GetMapping("/buscar")
    public ResponseEntity<List<Bolsa>> buscarBolsa(
            @RequestParam(required = false) Integer idBolsa,
            @RequestParam(required = false) String tipo) {

        List<Bolsa> resultado;

        if (idBolsa != null && tipo != null) {
            resultado = b.findByIdBolsaAndTipo(idBolsa, tipo);
        } else if (idBolsa != null) {
            resultado = b.findByIdBolsa(idBolsa);
        } else if (tipo != null) {
            resultado = b.findByTipo(tipo);
        } else {
            return ResponseEntity.badRequest().build(); // Nenhum parâmetro
        }

        return resultado.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(resultado);
    }

    // Editar apenas campos específicos
      @PatchMapping("/{id}")
        public ResponseEntity <Bolsa> editarBolsa(
        @PathVariable Integer id,
        @RequestBody Map<String, Object> dadosParaAtualizar)
        
        {
            Optional<Bolsa> bolsaOptional = b.findById(id);

            if(!bolsaOptional.isPresent())
            {
                return ResponseEntity.notFound().build();

            }

            Bolsa bolsaExistente = bolsaOptional.get();

            if(dadosParaAtualizar.containsKey("valor"))
                {
                    bolsaExistente.setValor((BigDecimal)dadosParaAtualizar.get("valor"));
                }

            if(dadosParaAtualizar.containsKey("tipo"))
            {
                bolsaExistente.setTipo((String)dadosParaAtualizar.get("tipo"));
            }

                b.save(bolsaExistente);
                return ResponseEntity.ok(bolsaExistente);

        }
       

    // Excluir bolsa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirBolsa(@PathVariable Integer id) {
        if (b.existsById(id)) {
            b.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
