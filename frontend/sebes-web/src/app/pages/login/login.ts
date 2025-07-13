import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '@services/auth.service';

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
    this.authService.solicitarAcesso(this.email);
    this.codigoEnviado = true;
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
