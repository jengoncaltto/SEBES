import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  imports: [],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
  
})
export class SidebarComponent {
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
