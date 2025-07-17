import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { UsuarioTipo } from '@models/enums/usuario.roles';

@Component({
  selector: 'app-sidebar',
  imports: [CommonModule, RouterLink],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css'
  
})
export class Sidebar{
  isDiscente = true;
  isLoggedIn = false;
  textoOpcaoInscricoes = "";

  constructor(private authService : AuthService) {}

  carregarOpcoesMenu(){
    if(this.authService.getTipo() == UsuarioTipo.DISCENTE){
      this.textoOpcaoInscricoes = "Minhas inscrições";
    } else{
      this.textoOpcaoInscricoes = "Inscrições Recebidas";
    }
  }

  isUsuarioLoggedIn(){
    this.isLoggedIn = this.authService.isLoggedIn();
    this.carregarOpcoesMenu();
  }
    
  logout(){
  	this.authService.logout();
  }
}
