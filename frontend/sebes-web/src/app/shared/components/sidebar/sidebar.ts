import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

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

  constructor() {
    if(this.isDiscente){
      this.textoOpcaoInscricoes = "Minhas inscrições";
    } else {
      this.textoOpcaoInscricoes = "Inscrições Recebidas";
    }
  }
}
