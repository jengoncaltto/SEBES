package br.uniriotec.prae.sebes.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import br.uniriotec.prae.sebes.Entity.Etapa;
import br.uniriotec.prae.sebes.Repositorio.EtapaRepository;
import br.uniriotec.prae.sebes.Repositorio.ProcessoSeletivoRepository;

@RestController
@RequestMapping("/etapa")
public class EtapaController {
    
    @Autowired
    EtapaRepository etapaRepository;

    @Autowired
    ProcessoSeletivoRepository processoSeletivoRepository;


    /* POST */
    
    @PostMapping
    public ResponseEntity<?> criarEtapa(@RequestBody Etapa novaEtapa) {
        // Verificar se processo seletivo existe
        if (novaEtapa.getProcessoSeletivo() == null ||
            novaEtapa.getProcessoSeletivo().getId() == null ||
            !processoSeletivoRepository.existsById(novaEtapa.getProcessoSeletivo().getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Processo seletivo inválido.");
        }

        // Verificar unicidade do tipoEtapa para esse processo seletivo
        List<Etapa> etapasDoProcesso = etapaRepository.findAllByProcessoSeletivoId(novaEtapa.getProcessoSeletivo().getId());
        boolean tipoExistente = etapasDoProcesso.stream()
                .anyMatch(e -> e.getTipoEtapa().equalsIgnoreCase(novaEtapa.getTipoEtapa()));

        if (tipoExistente) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe uma etapa do tipo '" + novaEtapa.getTipoEtapa() + "' para este processo seletivo.");
        }

        Etapa etapaSalva = etapaRepository.save(novaEtapa);
        return ResponseEntity.status(HttpStatus.CREATED).body(etapaSalva);
    }

    //* GET */
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<Etapa> result = etapaRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Bolsa com id " + id + " não encontrada.");
        }
    }

  
   @PatchMapping("/{id}")
   public ResponseEntity<Etapa> editarEtapas(@PathVariable String id, 
   Map<String, Object> dadosParaAtualizar)

   {
        Optional<Etapa> etapaOptional = etapaRepository.findById(id);
        
        if(!etapaOptional.isPresent())
            return ResponseEntity.notFound().build();
        
        Etapa etapaExiste = etapaOptional.get();

        if(dadosParaAtualizar.containsKey("data_inicio"))
        {
            etapaExiste.setDataInicio((LocalDateTime)dadosParaAtualizar.get("data_inicio"));
        }

        if(dadosParaAtualizar.containsKey("data_encerramento"))
        {
            etapaExiste.setDataEncerramento((LocalDateTime)dadosParaAtualizar.get("data_encerramento"));
        }

        if(dadosParaAtualizar.containsKey("status"))
        {
            etapaExiste.setStatus((String)dadosParaAtualizar.get("status"));
        }

        etapaRepository.save(etapaExiste);
        return ResponseEntity.ok(etapaExiste);
   }
   
   /* PATCH */
   
   @PatchMapping("/{id}")
   public ResponseEntity<?> atualizarParcial(@PathVariable String id, @RequestBody Map<String, Object> body) {
       Optional<Etapa> result = etapaRepository.findById(id);
       if (!result.isPresent()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etapa não encontrada.");
       }

       Etapa etapa = result.get();

       if (body.containsKey("dataInicio")) {
           try {
               String dataInicioStr = body.get("dataInicio").toString();
               LocalDateTime dataInicio = LocalDateTime.parse(dataInicioStr);
               etapa.setDataInicio(dataInicio);
           } catch (IllegalArgumentException e) {
               return ResponseEntity.badRequest().body("Formato inválido para dataInicio. Use yyyy-MM-dd HH:mm:ss[.fffffffff]");
           }
       }

       if (body.containsKey("dataEncerramento")) {
           try {
               String dataEncerramentoStr = body.get("dataEncerramento").toString();
               LocalDateTime dataEncerramento = LocalDateTime.parse(dataEncerramentoStr);
               etapa.setDataEncerramento(dataEncerramento);
           } catch (IllegalArgumentException e) {
               return ResponseEntity.badRequest().body("Formato inválido para dataEncerramento. Use yyyy-MM-dd HH:mm:ss[.fffffffff]");
           }
       }

       if (body.containsKey("status")) {
           etapa.setStatus(body.get("status").toString());
       }

       etapaRepository.save(etapa);
       return ResponseEntity.ok(etapa);
   }

   
   /**/
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deletarEtapa(@PathVariable String id) {
       if(etapaRepository.existsById(id)) {
    	   etapaRepository.deleteById(id);
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body("Etapa não encontrada.");
   }

}

//Criar (deve ser ligada a um processo)
//Editar (apenas datas e status)
//Excluir */
