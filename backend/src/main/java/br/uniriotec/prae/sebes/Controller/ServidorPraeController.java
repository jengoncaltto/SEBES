package com.example.sebes.Controller;

//import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sebes.Entity.ServidorPrae;
//import com.example.sebes.Entity.Usuario;
import com.example.sebes.Repositorio.ServidorPraeRepository;


@RestController
@RequestMapping("/servidor_praxe")
public class ServidorPraeController {
    
    @Autowired
    ServidorPraeRepository servidor;

    @PostMapping
    public ServidorPrae criaUsuario(@RequestBody ServidorPrae discente)
    {
        return servidor.save(discente);

    }

    
     @GetMapping("/buscar")
    public ResponseEntity<ServidorPrae> buscar(
    @RequestParam(required = false) Integer id,
    @RequestParam(required = false) String matricula) 
    {
        
        if(id != null)
        {
            Optional<ServidorPrae> resultadoPorID = servidor.findById(id);
            return ResponseEntity.ok(resultadoPorID.get());
        }
        else
            return ResponseEntity.badRequest().build();
    }//até aqui funciona
    

    @PutMapping("/{id}")
    public ResponseEntity<ServidorPrae> alterarStatus(@PathVariable Integer id)
    {
        Optional<ServidorPrae> resultado = servidor.findById(id);
        if(!resultado.isPresent())
                return ResponseEntity.notFound().build();
        
        ServidorPrae existe = resultado.get();
        
        //Inverte o status do usuario por conta do !(negacao)
        existe.setStatus(!existe.getStatus());

        servidor.save(existe);

        return ResponseEntity.ok(existe);
        
    }
   
   /*  @PatchMapping("/{id}")
    public ResponseEntity <ServidorPrae> atualizarUsuario(
        @PathVariable Integer id,
        @RequestBody Map<String, Object> dadosParaAtualizar) {
        
        Optional<ServidorPrae> servidorOptional = servidor.findById(id);

        if(!servidorOptional.isPresent())
        {
            return ResponseEntity.notFound().build();
        }

        ServidorPrae servidorExiste = servidorOptional.get();

        if(dadosParaAtualizar.containsKey("nome"))
        {
            servidorExiste.setNome((String)dadosParaAtualizar.get("nome"));
        }

        if(dadosParaAtualizar.containsKey("email_recuperacao"))
        {
            servidorExiste.setEmailRecuperacao((String)dadosParaAtualizar.get("email_recuperacao"));
        }



        servidor.save(servidorExiste);
        return ResponseEntity.ok(servidorExiste);
        
    } 
*/
    

    
   
}
