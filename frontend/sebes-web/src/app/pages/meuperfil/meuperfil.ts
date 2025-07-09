import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProcessoSeletivoDTO } from '../../core/models/ProcessoSeletivoResumidoDTO';

@Component({
  selector: 'app-meuperfil',
  imports: [CommonModule, RouterLink],
  templateUrl: './meuperfil.html',
  styleUrl: './meuperfil.css'
})
export class MeuPerfil {
  processos:ProcessoSeletivoDTO[]=[];
  constructor(){
    // Criando processos de exemplo
            const processo1 = new ProcessoSeletivoDTO('Mérito', '2025.1','Em andamento');
            const processo2 = new ProcessoSeletivoDTO('Pesquisa', '2024.2','Finalizada');
            const processo3 = new ProcessoSeletivoDTO('Mérito', '2024.1','Finalizada');
            const processo4 = new ProcessoSeletivoDTO('Pesquisa', '2023.2','Finalizada');
    
            this.processos.push(processo1, processo2, processo3, processo4);
  }
}
