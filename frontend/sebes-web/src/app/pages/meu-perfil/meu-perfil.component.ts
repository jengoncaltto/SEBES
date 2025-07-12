import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { ProcessoSeletivoService } from '@services/processo.service';
import { ProcessoSeletivoDto } from '@models/dtos/processo-seletivo.dto';
import { RespostaFormularioDto } from '@models/dtos/resposta-formulario.dto';
import { RespostaFormularioService } from '@services/resposta.service';
import { catchError, forkJoin, Observable, of, switchMap } from 'rxjs';


@Component({
  selector: 'app-meu-perfil',
  imports: [CommonModule, RouterLink],
  templateUrl: './meu-perfil.component.html',
  styleUrl: './meu-perfil.component.css'
})
export class MeuPerfilComponent implements OnInit {
  processos$!: Observable<ProcessoSeletivoDto[]>;

  constructor(
    private respostaService: RespostaFormularioService,
    private processoService: ProcessoSeletivoService
  ) {}

  ngOnInit(): void {
    const idUsuario = '1'; // <- Substitua por valor real (autenticado, por exemplo)
    this.processos$ = this.respostaService.buscarPorIdUsuario(idUsuario).pipe(
      switchMap((respostas: RespostaFormularioDto[]) => {
        const idsProcessos = respostas.map(r => r.idProcessoSeletivo);
        const requisicoes = idsProcessos.map(id => this.processoService.buscarPorId(id));
        return forkJoin(requisicoes); // junta todos os observables de processos
      }),
      catchError(err => {
        console.error('Erro ao buscar processos do usuário:', err);
        return of([]);
      })
    );
  }
}

