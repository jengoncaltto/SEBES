package br.uniriotec.prae.sebes.services;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.UsuarioDTO;
import br.uniriotec.prae.sebes.entity.LoginCode;
import br.uniriotec.prae.sebes.repository.LoginCodeRepository;
import br.uniriotec.prae.sebes.security.JwtTokenProvider;


@Service
public class LoginCodeService {

    @Autowired
    private LoginCodeRepository repository;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> enviarCodigoEmail(String email) {
        if (!emailValido(email)) {
            return ResponseEntity.badRequest().body("E-mail deve ser institucional da UNIRIO");
        }

    	if(!usuarioService.isEmailCadastrado(email)) {
            return ResponseEntity.badRequest().body("Email não cadastrado.");
    	}
        
        String code = gerarCodigo();
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(5);

        LoginCode loginCode = new LoginCode();
        loginCode.setEmail(email);
        loginCode.setCode(code);
        loginCode.setExpiration(expiration);

        repository.save(loginCode);

        enviarEmail(email, code);
        
        return ResponseEntity.ok("Código enviado para " + email);
    }
    
    private boolean emailValido(String email) {
        return email != null && email.matches("^[\\w.-]+@(edu\\.unirio\\.br|uniriotec\\.br)$");
    }
    
    private String gerarCodigo() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    private void enviarEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Seu código de login");
        message.setText("Seu código de login é: " + code + "\nEle expira em 5 minutos.");
        mailSender.send(message);
    }

    public ResponseEntity<?> validarCodigo(String email, String code) {
    	if(!usuarioService.isEmailCadastrado(email)) {
            return ResponseEntity.badRequest().body("Email não cadastrado.");
    	}
    	
        Optional<LoginCode> loginCodeOpt = repository.findTopByEmailOrderByExpirationDesc(email);

        if (loginCodeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum código de login solicitado.");
        }

        LoginCode loginCode = loginCodeOpt.get();

        if (!loginCode.getCode().equals(code)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código inválido!");
        }

        if (loginCode.getExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código expirado!");
        }

        if (loginCode.isUsed()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código já utilizado!");
        }

        loginCode.setUsed(true);
        repository.save(loginCode);

        String token = jwtTokenProvider.gerarToken(usuarioService.buscaPorEmail(email));

        return ResponseEntity.ok(Map.of("token", token));
    }

    public UsuarioDTO getIdDoUsuarioLogado(String token){
        return usuarioService.buscarPorId(jwtTokenProvider.getIdUsuario(token));
    }

}
