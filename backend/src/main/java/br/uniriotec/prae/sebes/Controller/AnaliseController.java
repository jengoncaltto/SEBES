package br.uniriotec.prae.sebes.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.Analise;
import br.uniriotec.prae.sebes.Repositorio.AnaliseRepository;

@RestController
@RequestMapping("/analise")
public class AnaliseController {

    @Autowired
    AnaliseRepository analise;

    @PostMapping
    public Analise criarAnalise(@RequestBody Analise a)
    {
        return analise.save(a);
    }

    @GetMapping
    public List<Analise> todasAnalises()
    {
        return analise.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Analise> getAnalisePorID(@PathVariable Integer id)
    {
        Optional<Analise> analiseExiste = analise.findById(id);
        if(analiseExiste.isPresent())
        {
            return ResponseEntity.ok(analiseExiste.get());
        }

        else    
            return ResponseEntity.notFound().build();
    }


    /*

    @GetMapping("/servidor/{id}")
    public ResponseEntity getAnalisePorServidor(@PathVariable Integer id) {
        Optional<Analise> resultado = analise.findByIdServidorPraxe(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/discente/{id}")
    public ResponseEntity getAnalisePorDiscente(@PathVariable Integer id) {
        Optional<Analise> resultado = analise.findByIdDiscente(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/inscricao/{id}")
    public ResponseEntity getAnalisePorInscricao(@PathVariable Integer id) {
        Optional<Analise> resultado = analise.findByIdInscricao(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/recurso/{id}")
    public ResponseEntity getAnalisePorRecurso(@PathVariable Integer id) {
        Optional<Analise> resultado = analise.findByIdRecurso(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        
     */

    
}


/*Criar
Buscar todos
Buscar específico (por id, por id do servidor, por id do discente, por id da inscrição ou do recurso) */
