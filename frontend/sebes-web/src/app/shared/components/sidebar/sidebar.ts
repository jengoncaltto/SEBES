import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-sidebar',
  imports: [RouterLink],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css'
  
})
export class Sidebar{
  isDiscente = true;
  textoOpcaoInscricoes = "";

  constructor(private authService : AuthService) {}
  	isLoggedIn = this.authService.isLoggedIn();
  
    if(this.authService.getTipo()?.includes('discente')){
      this.textoOpcaoInscricoes = "Minhas inscrições";
    } else{
      this.textoOpcaoInscricoes = "Inscrições Recebidas";
    }
    
  logout(){
  	this.authService.logout();
  }
}
