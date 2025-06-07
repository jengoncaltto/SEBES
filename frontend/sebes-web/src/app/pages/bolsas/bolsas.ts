import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { BolsaResumidaDTO } from '@core/models/BolsaResumidaDTO';

@Component({
  selector: 'app-bolsas',
  imports: [CommonModule, RouterLink],
  templateUrl: './bolsas.html',
  styleUrl: './bolsas.css'
})
export class Bolsas {
    bolsas: BolsaResumidaDTO[] = [];
    constructor() {
        // Criando bolsas de exemplo
        const bolsa1 = new BolsaResumidaDTO('Bolsa Mérito', 'Ajuda alunos testando o tamanho do texto pra ver se fica menor e o limite com ótimo desempenho.');
        const bolsa2 = new BolsaResumidaDTO('Bolsa Pesquisa', 'Para projetos de iniciação científica.');
        const bolsa3 = new BolsaResumidaDTO('Bolsa Mérito', 'Ajuda alunos com ótimo desempenho.');
        const bolsa4 = new BolsaResumidaDTO('Bolsa Pesquisa', 'Para projetos de iniciação científica.');

        this.bolsas.push(bolsa1, bolsa2, bolsa3, bolsa4, bolsa3, bolsa4);
    }
}
