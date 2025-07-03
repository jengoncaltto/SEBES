import { Component } from '@angular/core';
import { InscricaoResumidaDTO } from '../../../core/models/InscricaoResumidaDTO';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-detalhes-inscricao',
  imports: [CommonModule],
  templateUrl: './detalhes-inscricao.html',
  styleUrl: './detalhes-inscricao.css'
})
export class DetalhesInscricao {
  inscricoes : InscricaoResumidaDTO[]=[];
  constructor(){
      
  const inscricao1 = new InscricaoResumidaDTO('Comprovante de Renda', 'Enviado');
  const inscricao2 = new InscricaoResumidaDTO('Comprovante de Residência', 'Pendente');
  const inscricao3 = new InscricaoResumidaDTO('Documento', 'Enviado');
  const inscricao4 = new InscricaoResumidaDTO('Documento', 'Pendente');

  this.inscricoes.push(inscricao1, inscricao2, inscricao3, inscricao4, inscricao3, inscricao4);
  }

}
