import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProcessoSeletivoDto } from '@models/dtos/processo-seletivo.dto';
import { Observable } from 'rxjs';
import { UsuarioService } from '@services/usuario.service';
import { AuthService } from '@services/auth.service';
import { UsuarioDto } from '@models/dtos/usuario.dto';

@Component({
  selector: 'app-meu-perfil',
  imports: [CommonModule],
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
  ngOnInit(): void {
  this.authService.getUserLoggedIn().subscribe({
    next: result => {
      this.usuario = result;
    },
    error: err => {
      console.error('Erro:', err);
    }
  });
  }
}