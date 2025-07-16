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
  isLogado = true;

  constructor(private authService : AuthService) {
    //Ta errado isso ainda
      if(this.authService.getTipo()?.includes('discente')){
      this.textoOpcaoInscricoes = "Minhas inscrições";
    } else{
      this.textoOpcaoInscricoes = "Inscrições Recebidas";
    }
    //Colocar um else caso tipo seja null??
    
  }
}
