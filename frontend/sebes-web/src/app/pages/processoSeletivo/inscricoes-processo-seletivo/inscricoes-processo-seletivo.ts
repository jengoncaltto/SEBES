import { Component } from '@angular/core';
import { SelecaoBolsaComponent } from '../../../shared/components/selecao-bolsa/selecao-bolsa.component';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-inscricoes-processo-seletivo',
  imports: [SelecaoBolsaComponent, RouterLink],
  templateUrl: './inscricoes-processo-seletivo.html',
  styleUrl: './inscricoes-processo-seletivo.css'
})
export class InscricoesProcessoSeletivo {

}
