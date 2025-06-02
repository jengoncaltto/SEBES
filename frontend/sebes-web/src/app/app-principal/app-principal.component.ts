import { Component } from '@angular/core';
import { LoginComponent } from "./login/login.component";
import { CadastroFuncionarioComponent } from "../cadastro/cadastro-funcionario/cadastro-funcionario.component";
import { CadastroAlunoComponent } from "../cadastro/cadastro-aluno/cadastro-aluno.component";

@Component({
  selector: 'app-app-principal',
  imports: [LoginComponent, CadastroAlunoComponent, CadastroFuncionarioComponent, CadastroAlunoComponent],
  templateUrl: './app-principal.component.html',
  styleUrl: './app-principal.component.css'
})
export class AppPrincipalComponent {

}
