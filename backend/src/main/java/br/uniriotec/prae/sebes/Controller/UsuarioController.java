package br.uniriotec.prae.sebes.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.Usuario;
import br.uniriotec.prae.sebes.Repositorio.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    /* GET */
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Usuário não encontrado.");
        }
    }
    
    /* PATCH */
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable String id, @RequestBody Usuario request) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id " + id + " não encontrado.");
        }

        Usuario usuario = result.get();

        if (request.getNome() != null) usuario.setNome(request.getNome());
        if (request.getNomeSocial() != null) usuario.setNomeSocial(request.getNomeSocial());
        if (request.getEmailRecuperacao() != null) usuario.setEmailRecuperacao(request.getEmailRecuperacao());
        if (request.getTelefone() != null) usuario.setTelefone(request.getTelefone());
        if (request.getStatus() != null) usuario.setStatus(request.getStatus());

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

}
