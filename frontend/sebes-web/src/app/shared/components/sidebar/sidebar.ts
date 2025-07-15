import { Component } from '@angular/core';
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
    if(this.authService.isLoggedIn()){
      if(this.authService.getTipo()?.toLowerCase() == "discente"){
      this.textoOpcaoInscricoes = "Minhas inscrições";
    } else if(this.authService.getTipo()?.toLowerCase() == "servidor"){
      this.textoOpcaoInscricoes = "Inscrições Recebidas";
    }
    //Colocar um else caso tipo seja null??
    }
  }
}
