import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { ProcessoSeletivoDto } from '@models/dtos/processo-seletivo.dto';
import { ProcessoSeletivoService } from '@services/processo.service';



@Component({
  selector: 'app-processos-seletivos',
  imports: [CommonModule, RouterLink],
  templateUrl: './processos-seletivos.html',
  styleUrl: './processos-seletivos.css'
})
export class ProcessosSeletivos implements OnInit{
  processos:ProcessoSeletivoDto[]=[];

  constructor(private processoService:ProcessoSeletivoService){}

  ngOnInit(): void {
    this.carregarProcessos();
  }
  private carregarProcessos():void{
    this.processoService.listarTodos().subscribe({
      next: (dados:ProcessoSeletivoDto[]) => {
        this.processos=dados;
      },
      error:(err : any) => {
        console.log('Erro ao carregar processos', err);
      }
    })
  }
}
