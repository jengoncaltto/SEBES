package br.uniriotec.prae.sebes.Controller;

import java.time.LocalDateTime;
import java.util.List;
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

import br.uniriotec.prae.sebes.Entity.Bolsa;
import br.uniriotec.prae.sebes.Entity.Publicacao;
import br.uniriotec.prae.sebes.Entity.ServidorPrae;
import br.uniriotec.prae.sebes.Repositorio.BolsaRepository;
import br.uniriotec.prae.sebes.Repositorio.PublicacaoRepository;
import br.uniriotec.prae.sebes.Repositorio.ServidorPraeRepository;
import br.uniriotec.prae.sebes.dto.PublicacaoRequest;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private ServidorPraeRepository servidorRepository;

    @Autowired
    private BolsaRepository bolsaRepository;

    // Criar nova publicação
    @PostMapping("/publicar")
    public ResponseEntity<?> publicar(@RequestBody PublicacaoRequest request) {
        // Validações manuais
        if (request.getConteudo() == null || request.getConteudo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("O conteúdo da publicação é obrigatório.");
        }

        if (request.getIdServidor() == null) {
            return ResponseEntity.badRequest().body("O ID do servidor é obrigatório.");
        }

        if (request.getIdBolsa() == null) {
            return ResponseEntity.badRequest().body("O ID da bolsa é obrigatório.");
        }

        // Buscar entidades
        Optional<ServidorPrae> servidorOpt = servidorRepository.findById(request.getIdServidor());
        if (servidorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado.");
        }

        Optional<Bolsa> bolsaOpt = bolsaRepository.findById(request.getIdBolsa());
        if (bolsaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bolsa não encontrada.");
        }

        // Criar e salvar publicação
        Publicacao publicacao = new Publicacao();
        publicacao.setConteudo(request.getConteudo());
        publicacao.setServidor(servidorOpt.get());
        publicacao.setBolsa(bolsaOpt.get());
        
        LocalDateTime dataHoraPublicacao = LocalDateTime.now();
        publicacao.setDataPublicacao(dataHoraPublicacao);
        publicacao.setDataAtualizacao(dataHoraPublicacao);

        Publicacao salva = publicacaoRepository.save(publicacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    /* GET */
    
    @GetMapping
    public List<Publicacao> listarTodas() {
        return publicacaoRepository.findAllByOrderByDataPublicacaoDesc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Optional<Publicacao> result = publicacaoRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Discente não encontrado.");
        }
    }

    @GetMapping("/servidor/{idServidor}")
    public List<Publicacao> buscarPorServidor(@PathVariable String idServidor) {
        return publicacaoRepository.findAllByServidor_Id(idServidor);
    }

    @GetMapping("/bolsa/{idBolsa}")
    public List<Publicacao> buscarPorBolsa(@PathVariable Integer idBolsa) {
        return publicacaoRepository.findAllByBolsa_Id(idBolsa);
    }
    
    /* PATCH */
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarPublicacao(@PathVariable Integer id, @RequestBody Publicacao publicacaoAtualizada) {
        Optional<Publicacao> result = publicacaoRepository.findById(id);

        if (!result.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicação não encontrada.");
        }

        Publicacao publicacao = result.get();

        // Atualiza os campos permitidos
        if (publicacaoAtualizada.getConteudo() != null && !publicacaoAtualizada.getConteudo().trim().isEmpty()) {
            publicacao.setConteudo(publicacaoAtualizada.getConteudo());
        }

        LocalDateTime dataHoraAtualizacao = LocalDateTime.now();
        // Sempre atualiza a data de atualização para o momento atual, ou usa a enviada (se desejar)
        publicacao.setDataAtualizacao(dataHoraAtualizacao);

        publicacaoRepository.save(publicacao);

        return ResponseEntity.ok(publicacao);
    }


    
    /* DELETE */
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPublicacao(@PathVariable Integer id) {
        if (!publicacaoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Publicação com ID " + id + " não encontrada.");
        }
        publicacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
