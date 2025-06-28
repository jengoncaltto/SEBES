package br.uniriotec.prae.sebes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.Entity.Discente;
import br.uniriotec.prae.sebes.Entity.ServidorPrae;
import br.uniriotec.prae.sebes.Entity.Usuario;
import br.uniriotec.prae.sebes.Repositorio.DiscenteRepository;
import br.uniriotec.prae.sebes.Repositorio.ServidorPraeRepository;
import br.uniriotec.prae.sebes.Repositorio.UsuarioRepository;
import br.uniriotec.prae.sebes.dto.CadastroDiscenteRequest;
import br.uniriotec.prae.sebes.dto.CadastroServidorRequest;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private ServidorPraeRepository servidorPraeRepository;
    
    @PostMapping("/discente")
    public ResponseEntity<?> cadastrarDiscente(@RequestBody CadastroDiscenteRequest request) {
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
        usuario.setStatus(true);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // Criar discente
        Discente discente = new Discente();
        discente.setUsuario(usuarioSalvo);
        discente.setCurso(request.getCurso());

        Discente discenteSalvo = discenteRepository.save(discente);

        return ResponseEntity.status(HttpStatus.CREATED).body(discenteSalvo);
    }

    @PostMapping("/servidor")
    public ResponseEntity<?> cadastrarServidor(@RequestBody CadastroServidorRequest request) {
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
        usuario.setStatus(true);

        Usuario usuarioCadastrado = usuarioRepository.save(usuario);

        // Criar servidor
        ServidorPrae servidor = new ServidorPrae();
        servidor.setUsuario(usuarioCadastrado);
        servidor.setCargo(request.getCargo());
        servidor.setSetor(request.getSetor());

        ServidorPrae servidorSalvo = servidorPraeRepository.save(servidor);

        return ResponseEntity.status(HttpStatus.CREATED).body(servidorSalvo);
    }

}
