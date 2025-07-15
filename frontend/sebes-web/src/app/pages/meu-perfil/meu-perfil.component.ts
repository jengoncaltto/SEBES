import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ProcessoSeletivoService } from '@services/processo.service';
import { ProcessoSeletivoDto } from '@models/dtos/processo-seletivo.dto';
import { catchError, EMPTY, forkJoin, Observable, of, switchMap } from 'rxjs';
import { UsuarioService } from '@services/usuario.service';
import { AuthService } from '@services/auth.service';
import { UsuarioDto } from '@models/dtos/usuario.dto';

@Component({
  selector: 'app-meu-perfil',
  imports: [CommonModule, RouterLink],
  templateUrl: './meu-perfil.component.html',
  styleUrls: ['./meu-perfil.component.css']
})
export class MeuPerfilComponent /*implements OnInit*/ {
  processos!: ProcessoSeletivoDto[];
  usuarioLogado$!: Observable<UsuarioDto>;
  usuario! : UsuarioDto;

  constructor(
    private usuarioService: UsuarioService,
    private authService: AuthService
  ) {}
  
}

  /*
ngOnInit(): void {
  this.authService.getUserLoggedIn().pipe(
  switchMap(usuario => {
    this.usuarioLogado$ = usuario; // já pega o valor aqui, se quiser
    return this.usuarioService.getById(usuario.id); // continua para outra requisição
  })
).subscribe({
  next: dadosCompletos => {
    this.usuario = dadosCompletos;
  },
  error: err => {
    console.error('Erro:', err);
  }
});

ngOnInit(): void {
    this.authService.getUserLoggedIn().pipe(
      switchMap(usuario => {
        if (!usuario?.id) {
          this.router.navigate(['/login']);
          return EMPTY;
        }
        // Guarda dados básicos
        this.usuarioLogado$ = usuario;

        // Busca dados completos no banco
        return this.usuarioService.getById(usuario.id);
      })
    ).subscribe({
      next: (dadosCompletos) => {
        this.usuarioLogado = dadosCompletos;
      },
      error: (err) => {
        console.error('Erro ao obter ou carregar usuário:', err);
      }
    });
anterior ao código jwt
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
    */


