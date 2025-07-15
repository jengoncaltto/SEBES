package br.uniriotec.prae.sebes.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uniriotec.prae.sebes.services.LoginCodeService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginCodeService loginCodeService;

    @PostMapping
    public ResponseEntity<?> enviarCodigoLogin(@RequestBody Map<String, Object> body) {
        String email = (String) body.get("email");
        
        return loginCodeService.enviarCodigoEmail(email);
    }

    @PostMapping("/verificar-codigo")
    public ResponseEntity<?> verificarCodigo(@RequestBody Map<String, Object> body) {
        String email = (String) body.get("email");
        String code = (String) body.get("codigo");

        return loginCodeService.validarCodigo(email, code);
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> getUsuarioLogado(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(loginCodeService.getIdDoUsuarioLogado((String) body.get("token")));
    }
}
