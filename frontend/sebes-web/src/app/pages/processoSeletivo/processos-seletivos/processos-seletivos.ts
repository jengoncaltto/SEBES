import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProcessoSeletivoResumidoDTO } from '../../../core/models/ProcessoSeletivoResumidoDTO';


@Component({
  selector: 'app-processos-seletivos',
  imports: [RouterLink , CommonModule],
  templateUrl: './processos-seletivos.html',
  styleUrl: './processos-seletivos.css'
})
export class ProcessosSeletivos {
      publicacoes: ProcessoSeletivoResumidoDTO[] = [];
      constructor() {
          // Criando bolsas de exemplo
          const processo1 = new ProcessoSeletivoResumidoDTO('Bolsa Mérito', 'Ajuda alunos testando o tamanho do texto pra ver se fica menor e o limite com ótimo desempenho.');
          const processo2 = new ProcessoSeletivoResumidoDTO('Bolsa Pesquisa', 'Para projetos de iniciação científica.');
          const processo3 = new ProcessoSeletivoResumidoDTO('Bolsa Mérito', 'Ajuda alunos com ótimo desempenho.');
          const processo4 = new ProcessoSeletivoResumidoDTO('Bolsa Pesquisa', 'Para projetos de iniciação científica.');
  
          this.publicacoes.push(processo1, processo2, processo3, processo4, processo3, processo4);
      }
}
