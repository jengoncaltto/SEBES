import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CadastroFuncionarioComponent } from "./components/cadastro/cadastro-funcionario/cadastro-funcionario.component";
import { NavbarComponent } from "./components/shared/navbar/navbar.component";
import { LoginComponent } from "./components/login/login.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CadastroFuncionarioComponent, NavbarComponent, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'sebes-web';
}
