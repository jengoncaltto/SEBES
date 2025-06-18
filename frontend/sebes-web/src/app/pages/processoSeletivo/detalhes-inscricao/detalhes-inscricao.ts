import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { testeDTO } from '../../../core/models/teste';

@Component({
  selector: 'app-detalhes-inscricao',
  imports: [RouterLink],
  templateUrl: './detalhes-inscricao.html',
  styleUrl: './detalhes-inscricao.css'
})
export class DetalhesInscricao {
  documentos: testeDTO[] = [];
  construtor(){
    const doc1 = new testeDTO('1','ola');
    const doc2 = new testeDTO('2','bla');
    const doc3 = new testeDTO('1','ola');

    this.documentos.push(doc1,doc2,doc3);
  }
}
