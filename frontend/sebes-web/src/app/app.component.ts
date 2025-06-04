import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './componentes-gerais/navbar/navbar.component';
import { EditalComponent } from './editais/edital/edital.component';
import { CadastroAlunoComponent } from './cadastro/cadastro-aluno/cadastro-aluno.component';
import { CadastroFuncionarioComponent } from './cadastro/cadastro-funcionario/cadastro-funcionario.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, CadastroAlunoComponent, CadastroFuncionarioComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'sebes-web';
}
