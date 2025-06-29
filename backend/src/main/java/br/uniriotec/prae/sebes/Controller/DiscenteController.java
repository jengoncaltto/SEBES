package br.uniriotec.prae.sebes.Controller;

//import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.Discente;
import br.uniriotec.prae.sebes.Entity.Usuario;
import br.uniriotec.prae.sebes.Repositorio.DiscenteRepository;
import br.uniriotec.prae.sebes.Repositorio.UsuarioRepository;
import br.uniriotec.prae.sebes.dto.DiscenteRequest;


@RestController
@RequestMapping("/usuarios/discentes")
public class DiscenteController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DiscenteRepository discenteRepository;

    /* POST */

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarDiscente(@RequestBody DiscenteRequest request) {
        // Validações básicas
        if (request.getNome() == null || request.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("O nome é obrigatório.");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("O email é obrigatório.");
        }
        if (request.getCurso() == null) {
            return ResponseEntity.badRequest().body("A matrícula é obrigatória.");
        }
        // Verifica se email já existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }
        
        // Criar usuário
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setNomeSocial(request.getNomeSocial());
        usuario.setEmail(request.getEmail());
        usuario.setEmailRecuperacao(request.getEmailRecuperacao());
        usuario.setTelefone(request.getTelefone());
        usuario.setStatus(request.getStatus());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // Criar discente
        Discente discente = new Discente();
        discente.setUsuario(usuarioSalvo);
        discente.setCurso(request.getCurso());

        Discente discenteSalvo = discenteRepository.save(discente);

        return ResponseEntity.status(HttpStatus.CREATED).body(discenteSalvo);
    }

    
    /* GET */
    
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(discenteRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<Discente> result = discenteRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Discente não encontrado.");
        }
    }
    
    /* PATCH */

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarDiscente(@PathVariable String id, @RequestBody DiscenteRequest request) {
        Optional<Discente> result = discenteRepository.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Discente não encontrado.");
        }

        Discente discente = result.get();
        Usuario usuario = discente.getUsuario();

        // Atualiza dados do usuário
        if (request.getNome() != null) usuario.setNome(request.getNome());
        if (request.getNomeSocial() != null) usuario.setNomeSocial(request.getNomeSocial());
        if (request.getEmailRecuperacao() != null) usuario.setEmailRecuperacao(request.getEmailRecuperacao());
        if (request.getTelefone() != null) usuario.setTelefone(request.getTelefone());
        if (request.getStatus() != null) usuario.setStatus(request.getStatus());

        // Atualiza dados específicos do discente
        if (request.getCurso() != null) discente.setCurso(request.getCurso());

        usuarioRepository.save(usuario);
        discenteRepository.save(discente);

        return ResponseEntity.ok(discente);
    }
    
    /* DELETE */
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDiscente(@PathVariable String id) {
        Optional<Discente> discenteOpt = discenteRepository.findById(id);
        if (!discenteOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Discente não encontrado.");
        }

        discenteRepository.deleteById(id);
        usuarioRepository.deleteById(id); // Deleta o usuário vinculado
        return ResponseEntity.ok("Discente e usuário deletados com sucesso.");
    }
   
}
