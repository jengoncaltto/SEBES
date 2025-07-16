import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioDto, UsuarioRequest } from '@models/dtos/usuario.dto';
import { DiscenteDto, DiscenteRequest } from '@models/dtos/discente.dto';
import { ServidorPraeDto, ServidorRequest } from '@models/dtos/servidor-prae.dto';
import { UsuarioTipo } from '@models/enums/usuario.roles';
import { DiscenteService, ServidorPraeService, UsuarioService } from '@services/index';

@Component({
  selector: 'cadastro',
  templateUrl: './cadastro.html',
  styleUrls: ['./cadastro.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Cadastro {
  usuario: UsuarioDto = { id: null, nomeUsuario: '', email: '', emailRecuperacao: null, tipo: '' };

  discente: DiscenteDto = {
    id: null, nome: '', nomeSocial: '', telefone: '', matricula: '', idUsuario: null };

  servidor: ServidorPraeDto = {
    id: null, nome: '', nomeSocial: '', telefone: '', setor: '', cargo: '', idUsuario: null };


  erroEmail = '';
  erroGeral = '';

  usuarioTipo = UsuarioTipo;

  constructor(private router: Router,
              private usuarioService: UsuarioService, 
              private discenteService: DiscenteService,
              private servidorService: ServidorPraeService) {}

  onSubmit(): void {
    if (!this.validacoes()) {
      return;
    }

    this.usuarioService.create(new UsuarioRequest(this.usuario)).subscribe({
      next: (usuarioCriado) => {
        this.usuario.id = usuarioCriado.id;

        if (this.usuario.tipo === UsuarioTipo.DISCENTE) {
          this.discente.idUsuario = this.usuario.id!;
          this.discenteService.create(new DiscenteRequest(this.discente)).subscribe({
            next: () => this.router.navigate(['/login']),
            error: (err) => console.error('Erro ao criar discente:', err)
          });
        }

        if (this.usuario.tipo === UsuarioTipo.SERVIDOR) {
          this.servidor.idUsuario = this.usuario.id!;
          this.servidorService.create(new ServidorRequest(this.servidor)).subscribe({
            next: () => this.router.navigate(['/login']),
            error: (err) => console.error('Erro ao criar servidor:', err)
          });
        }
      },
      error: (err) => {
        console.error('Erro ao criar usuário:', err);
      }
    });
  }

  validacoes(){
    // usuário básico
    if (!this.usuario.nomeUsuario.trim()) {
      this.erroGeral = 'Informe o nome de usuário.';
      return false;
    }
    if (!this.usuario.email.trim() || !this.validarEmail(this.usuario.email)) {
      this.erroEmail = 'Informe um email válido.';
      return false;
    }
    // exemplo: checa email duplicado via sync (você pode trocar por async)
    this.usuarioService.isEmailCadastrado(this.usuario.email).subscribe({
      next: (existe) => {
        this.erroEmail = '';
        if (existe) {
          this.erroEmail = 'Email já está em uso.';
        }
      },
      error: (err) => { this.erroEmail = 'Erro ao validar email. Tente novamente.'}
    });
    if (this.usuario.tipo == null) {
      this.erroGeral = 'Selecione o tipo de usuário.';
      return false;
    }

    // dados específicos
    if (this.usuario.tipo === UsuarioTipo.DISCENTE) {
      if (!this.discente.nome.trim()) {
        this.erroGeral = 'Informe o nome completo do discente.';
        return false;
      }
      /*******************/
      if(this.discente.matricula.trim().length != 11){
        this.erroGeral = 'A matrícula deve ter 11 dígitos'
        return false;
      }
      /*******************/
      if (!this.discente.matricula.trim()) {
        this.erroGeral = 'Informe a matrícula.';
        return false;
      }
      if (!this.discente.telefone.trim()) {
        this.erroGeral = 'Informe o telefone.';
        return false;
      }
      /*******************/
      if(this.discente.telefone.trim().length > 15){
        this.erroGeral = 'O telefone deve ter no máximo 15 dígitos'
        return false;
      }
      /*******************/
      this.discente.idUsuario = '';
    } 
    
    if(this.usuario.tipo === UsuarioTipo.SERVIDOR){
      if (!this.servidor.nome.trim()) {
        this.erroGeral = 'Informe o nome completo do servidor.';
        return false;
      }
      if (!this.servidor.telefone.trim()) {
        this.erroGeral = 'Informe o telefone.';
        return false;
      }
      if (!this.servidor.cargo.trim()) {
        this.erroGeral = 'Informe o cargo.';
        return false;
      }
      if (!this.servidor.setor.trim()) {
        this.erroGeral = 'Informe o setor.';
        return false;
      }
      this.servidor.idUsuario = '';
    }

    // nenhum erro
    this.erroEmail = '';
    this.erroGeral = '';
    return true;
  }

  private validarEmail(email: string): boolean {
    // Verifica se o email tem formato básico válido
    const formatoBasico = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!formatoBasico.test(email)) return false;

    // Verifica se termina com os domínios permitidos
    return email.endsWith('@edu.unirio.br') || email.endsWith('@uniriotec.br');
  }

}
