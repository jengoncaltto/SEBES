package com.example.sebes.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.sebes.Entity.Inscricao;
import com.example.sebes.Repositorio.InscricaoRepository;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {
    
    @Autowired
    InscricaoRepository inscricao;


    @PostMapping
    public Inscricao criarInscricao(@RequestBody Inscricao insc)
    {
        return inscricao.save(insc);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscarPorID(@PathVariable Integer id)
    {
        Optional<Inscricao> resultado = inscricao.findById(id);
        if(!resultado.isPresent())
            {
                return ResponseEntity.notFound().build();
            }
        return ResponseEntity.ok(resultado.get());
    }


    @GetMapping
    public List<Inscricao>buscarTodasInscricoes()
    {
        return inscricao.findAll();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Inscricao> editarInscricao
        (@PathVariable Integer id, 
        @RequestBody Map<String, Object> dadosParaAtualizar)
    {
            Optional<Inscricao> resultado = inscricao.findById(id);
            if(!resultado.isPresent())
            {   
                return ResponseEntity.notFound().build();
            }

            Inscricao inscricaoExiste = resultado.get();

            if(dadosParaAtualizar.containsKey("status"))
                {
                    inscricaoExiste.setStatus((String)dadosParaAtualizar.get("status"));
            }
            if(dadosParaAtualizar.containsKey("id_analise"))
                {
                    inscricaoExiste.setStatus((String)dadosParaAtualizar.get("id_analise"));
            }

            inscricao.save(inscricaoExiste);
            return ResponseEntity.ok(inscricaoExiste);
    }


}

/*Criar - ok
Buscar (por id) ok
Buscar todos (por processo, por discente) ok
Editar (apenas status e id analise) ok
 */
