package br.uniriotec.prae.sebes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Discente cadastrarDiscente(@RequestBody CadastroDiscenteRequest request) {
        // Criar o usuário
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setNomeSocial(request.getNomeSocial());
        usuario.setEmail(request.getEmail());
        usuario.setEmailRecuperacao(request.getEmailRecuperacao());
        usuario.setTelefone(request.getTelefone());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // Criar o discente vinculado ao usuário
        Discente discente = new Discente();
        discente.setUsuario(usuarioSalvo); // o ID será herdado via @MapsId
        discente.setMatricula(request.getMatricula());

        return discenteRepository.save(discente);
    }
    
    @PostMapping("/servidor")
    public ServidorPrae cadastrarServidor(@RequestBody CadastroServidorRequest request) {
        // Criar o usuário
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setNomeSocial(request.getNomeSocial());
        usuario.setEmail(request.getEmail());
        usuario.setEmailRecuperacao(request.getEmailRecuperacao());
        usuario.setTelefone(request.getTelefone());

        Usuario usuarioCadastrado = usuarioRepository.save(usuario);

        // Criar o discente vinculado ao usuário
        ServidorPrae servidor = new ServidorPrae();
        servidor.setUsuario(usuarioCadastrado); // o ID será herdado via @MapsId
        servidor.setCargo(request.getCargo());
        servidor.setSetor(request.getSetor());

        return servidorPraeRepository.save(servidor);
    }
}
