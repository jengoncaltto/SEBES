import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './app-principal/componentes-gerais/header/header.component';
import { BolsaComponent } from './bolsas-oferecidas/bolsa/bolsa.component';
import { CadastroAlunoComponent } from './cadastro/cadastro-aluno/cadastro-aluno.component';
import { CadastroFuncionarioComponent } from './cadastro/cadastro-funcionario/cadastro-funcionario.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, CadastroAlunoComponent,CadastroFuncionarioComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'sebes-web';
}
