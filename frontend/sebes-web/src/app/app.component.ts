import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/shared/navbar/navbar.component";
import { SidebarComponent } from "./components/shared/sidebar/sidebar.component";
import { BolsasComponent } from "./components/bolsas/bolsas.component";
import { InscricaoComponent } from "./components/discente/inscricao/inscricao.component";
import { InscricoesComponent } from "./components/editais/inscricoes/inscricoes.component";
import { EditaisComponent } from "./components/editais/editais.component";
import { EditalComponent } from "./components/editais/edital/edital.component";
import { LoginComponent } from "./components/login/login.component";
import { CadastroAlunoComponent } from "./components/cadastro/cadastro-aluno/cadastro-aluno.component";
import { CadastroFuncionarioComponent } from "./components/cadastro/cadastro-funcionario/cadastro-funcionario.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, SidebarComponent, BolsasComponent, InscricaoComponent, InscricoesComponent, EditaisComponent, EditalComponent, LoginComponent, CadastroAlunoComponent, CadastroFuncionarioComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'sebes-web';
}
