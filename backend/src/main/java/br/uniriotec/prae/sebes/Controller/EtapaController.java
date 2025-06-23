package com.example.sebes.Controller;

import java.security.Timestamp;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.sebes.Entity.Etapa;
import com.example.sebes.Repositorio.EtapaRepository;
import com.example.sebes.Repositorio.ProcessoSeletivoRepository;

@RestController
@RequestMapping("/etapa")
public class EtapaController {
    
    @Autowired
    EtapaRepository etapa;

    @Autowired
    ProcessoSeletivoRepository processo;


    @PostMapping
    public ResponseEntity<Etapa> criarEtapa(@RequestBody Etapa et)
    {
        if (et.getProcessoSeletivo() == null || et.getProcessoSeletivo().getId() == null) {
            return ResponseEntity.badRequest().body(null);
    }

    // Verifica se o processo seletivo com esse ID existe no banco
    if (!processo.existsById(et.getProcessoSeletivo().getId())) {
        return ResponseEntity.badRequest().build();
    }
        Etapa etapaNova = etapa.save(et);
        return ResponseEntity.ok(etapaNova);
    }


  
   @PatchMapping("/{id}")
   public ResponseEntity<Etapa> editarEtapas(@PathVariable Integer id, 
   Map<String, Object> dadosParaAtualizar)

   {
        Optional<Etapa> etapaOptional = etapa.findById(id);
        
        if(!etapaOptional.isPresent())
            return ResponseEntity.notFound().build();
        
        Etapa etapaExiste = etapaOptional.get();

        if(dadosParaAtualizar.containsKey("data_inicio"))
        {
            etapaExiste.setDataInicio((Timestamp)dadosParaAtualizar.get("data_inicio"));
        }

        if(dadosParaAtualizar.containsKey("data_encerramento"))
        {
            etapaExiste.setDataEncerramento((Timestamp)dadosParaAtualizar.get("data_encerramento"));
        }

        if(dadosParaAtualizar.containsKey("status"))
        {
            etapaExiste.setStatus((String)dadosParaAtualizar.get("status"));
        }

        etapa.save(etapaExiste);
        return ResponseEntity.ok(etapaExiste);
   }
   
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Etapa> excluirEtapa(@PathVariable Integer id)
   {
        if(etapa.existsById(id))
        {
            etapa.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.noContent().build();
   }

}

//Criar (deve ser ligada a um processo)
//Editar (apenas datas e status)
//Excluir */
