package br.uniriotec.prae.sebes.controller;

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

import br.uniriotec.prae.sebes.dto.ServidorDTO;
import br.uniriotec.prae.sebes.entity.ServidorPrae;
import br.uniriotec.prae.sebes.entity.Usuario;
import br.uniriotec.prae.sebes.repository.ServidorPraeRepository;
import br.uniriotec.prae.sebes.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuarios/servidores")
public class ServidorPraeController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ServidorPraeRepository servidorRepository;

    /* POST */

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarServidor(@RequestBody ServidorDTO request) {
        // Validações básicas
        if (request.getNome() == null || request.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("O nome é obrigatório.");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("O email é obrigatório.");
        }
        if (request.getCargo() == null || request.getCargo().isBlank()) {
            return ResponseEntity.badRequest().body("O cargo é obrigatório.");
        }
        if (request.getSetor() == null || request.getSetor().isBlank()) {
            return ResponseEntity.badRequest().body("O setor é obrigatório.");
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

        Usuario usuarioCadastrado = usuarioRepository.save(usuario);

        // Criar servidor
        ServidorPrae servidor = new ServidorPrae();
        servidor.setUsuario(usuarioCadastrado);
        servidor.setCargo(request.getCargo());
        servidor.setSetor(request.getSetor());

        ServidorPrae servidorSalvo = servidorRepository.save(servidor);

        return ResponseEntity.status(HttpStatus.CREATED).body(servidorSalvo);
    }


    /* GET */

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(servidorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<ServidorPrae> result = servidorRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Servidor(a) PRAE não encontrado(a).");
        }
    }

    /* PATCH */

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarServidor(@PathVariable String id, @RequestBody ServidorDTO request) {
        Optional<ServidorPrae> result = servidorRepository.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor(a) não encontrado(a).");
        }

        ServidorPrae servidor = result.get();
        Usuario usuario = servidor.getUsuario();

        // Atualiza dados do usuário
        if (request.getNome() != null)
            usuario.setNome(request.getNome());
        if (request.getNomeSocial() != null)
            usuario.setNomeSocial(request.getNomeSocial());
        if (request.getEmailRecuperacao() != null)
            usuario.setEmailRecuperacao(request.getEmailRecuperacao());
        if (request.getTelefone() != null)
            usuario.setTelefone(request.getTelefone());
        if (request.getStatus() != null)
            usuario.setStatus(request.getStatus());

        // Atualiza dados específicos do servidor
        if (request.getCargo() != null)
            servidor.setCargo(request.getCargo());
        if (request.getSetor() != null)
            servidor.setSetor(request.getSetor());

        usuarioRepository.save(usuario);
        servidorRepository.save(servidor);

        return ResponseEntity.ok(servidor);
    }

    /* DELETE */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarServidor(@PathVariable String id) {
        Optional<ServidorPrae> servidorOpt = servidorRepository.findById(id);
        if (!servidorOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado.");
        }

        servidorRepository.deleteById(id);
        usuarioRepository.deleteById(id); // Deleta o usuário vinculado
        return ResponseEntity.ok("Servidor e usuário deletados com sucesso.");
    }


}
