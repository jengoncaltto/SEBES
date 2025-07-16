import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { UsuarioService } from '@services/index';

@Component({
  selector: 'login',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login{
  erroEmail: string = '';

  email: string = '';
  codigo: string = '';
  codigoEnviado = false;

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    if (this.email == null || this.email == '') {
		this.erroEmail = 'Digite um email válido.';
		return;
	}
    if (!this.email.trim() || !this.validarEmail(this.email)) {
        this.erroEmail = 'Informe um email válido.';
        return;
    }
    
    this.usuarioService.isEmailCadastrado(this.email).subscribe({
      next: (existe) => {
        this.erroEmail = '';
        if (!existe) {
          this.erroEmail = 'Email não cadastrado.';
        }
      },
      error: (err) => { this.erroEmail = 'Erro ao validar email. Tente novamente.'}
    });
    this.authService.solicitarAcesso(this.email);
    this.codigoEnviado = true;
  }
  
  private validarEmail(email: string): boolean {
    // Verifica se o email tem formato básico válido
    const formatoBasico = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!formatoBasico.test(email)) return false;

    // Verifica se termina com os domínios permitidos
    return email.endsWith('@edu.unirio.br') || email.endsWith('@uniriotec.br');
  }
  

  onVerificarCodigo(): void {
  	if (this.codigo == null || this.codigo == '') return;

    this.authService.validarAcesso(this.email, this.codigo).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);

        if (this.authService.isLoggedIn()) {
          this.router.navigate(['/perfil']);
        } else {
          alert('Token inválido ou expirado.');
        }
      },
      error: (err) => {
        console.error('Erro ao validar código:', err);
        alert('Código inválido ou expirado.');
      }
    });
  }

}
