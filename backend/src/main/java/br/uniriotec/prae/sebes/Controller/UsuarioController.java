package br.uniriotec.prae.sebes.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.Discente;
import br.uniriotec.prae.sebes.Entity.Usuario;
import br.uniriotec.prae.sebes.Repositorio.DiscenteRepository;
import br.uniriotec.prae.sebes.Repositorio.UsuarioRepository;
import br.uniriotec.prae.sebes.dto.CadastroDiscenteRequest;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    

    /* PUT */
    @PatchMapping("/{id}")
    public ResponseEntity <Usuario> atualizarUsuario(
        @PathVariable String id,
        @RequestBody Map<String, Object> dadosParaAtualizar) {
        
        Optional<Usuario> estudanteOptional = usuarioRepository.findById(id);

        if(!estudanteOptional.isPresent())
        {
            return ResponseEntity.notFound().build();
        }

        Usuario estudanteExiste = estudanteOptional.get();

        if(dadosParaAtualizar.containsKey("nome"))
        {
            estudanteExiste.setNome((String)dadosParaAtualizar.get("nome"));
        }

        if(dadosParaAtualizar.containsKey("email_recuperacao"))
        {
            estudanteExiste.setEmailRecuperacao((String)dadosParaAtualizar.get("email_recuperacao"));
        }



        usuarioRepository.save(estudanteExiste);
        return ResponseEntity.ok(estudanteExiste);
        
    } 
    
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarEstudante(
    @RequestParam(required = false) String id,
    @RequestParam(required = false) String matricula) 
    {
        
        if(id != null)
        {
            Optional<Usuario> resultadoPorID = usuarioRepository.findById(id);
            return ResponseEntity.ok(resultadoPorID.get());
        }
        else
            return ResponseEntity.badRequest().build();
    }//até aqui funciona
    

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarStatus(@PathVariable String id)
    {
        Optional<Usuario> resultado = usuarioRepository.findById(id);
        if(!resultado.isPresent())
                return ResponseEntity.notFound().build();
        
        Usuario usuarioExiste = resultado.get();
        
        //Inverte o status do usuario por conta do !(negacao)
        usuarioExiste.setStatus(!usuarioExiste.getStatus());

        usuarioRepository.save(usuarioExiste);

        return ResponseEntity.ok(usuarioExiste);
        
    }
}


/*Criar - ok
Buscar (por Id, por matricula)
-> apenas nome e email
Editar
-> apenas nome, email de recuperação
Sem excluir, apenas mudar status para inativo */