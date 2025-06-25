package br.uniriotec.prae.sebes.Controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import br.uniriotec.prae.sebes.Entity.ProcessoSeletivo;
import br.uniriotec.prae.sebes.Repositorio.ProcessoSeletivoRepository;



@RestController
@RequestMapping("/processo_seletivo")
public class ProcessoSeletivoController {
    
    @Autowired
    ProcessoSeletivoRepository processo;
    
    
    
    @PostMapping
    public ProcessoSeletivo criaProcessoSeletivo(@RequestBody ProcessoSeletivo prs)
    {
        return processo.save(prs);
    }

    @GetMapping
    public List<ProcessoSeletivo> getProcessos()
    {
        return processo.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProcessoSeletivo> buscarPorId(@PathVariable Integer id)
    {
        Optional<ProcessoSeletivo> resultado = processo.findById(id);

        if (resultado.isPresent()) {

            return ResponseEntity.ok(resultado.get());
        } 
        
        return ResponseEntity.notFound().build();
    }
    


    @PatchMapping("/{id}")
    public ResponseEntity <ProcessoSeletivo> editarProcesso(
        @PathVariable Integer id,
        @RequestBody Map<String, Object> dadosParaAtualizar)
    {
        
        Optional<ProcessoSeletivo> processOptional = processo.findById(id);

        if(!processOptional.isPresent())
        {
            return ResponseEntity.notFound().build();
        }

        ProcessoSeletivo processoExiste = processOptional.get();

        if(dadosParaAtualizar.containsKey("status"))
        {
            processoExiste.setStatus((String)dadosParaAtualizar.get("status"));
        }

        if(dadosParaAtualizar.containsKey("data_inicio"))
        {
            processoExiste.setDataInicio((Timestamp)dadosParaAtualizar.get("data_inicio"));
        }

        if(dadosParaAtualizar.containsKey("data_encerramento"))
        {
            processoExiste.setDataEncerramento((Timestamp)dadosParaAtualizar.get("data_encerramento"));
        }


        processo.save(processoExiste);
        return ResponseEntity.ok(processoExiste);
        
    }
    

    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ProcessoSeletivo> excluirBolsa(@PathVariable Integer id)
    {
        if(processo.existsById(id))
        {
            processo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.notFound().build();
    }
    
}
