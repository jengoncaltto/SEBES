import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/shared/navbar/navbar.component";
import { SidebarComponent } from "./components/shared/sidebar/sidebar.component";
import { BolsaComponent } from './components/bolsas/bolsa/bolsa.component';
import { BolsasComponent } from "./components/bolsas/bolsas.component";
import { InscricaoComponent } from "./components/discente/inscricao/inscricao.component";
import { InscricoesComponent } from "./components/editais/inscricoes/inscricoes.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, SidebarComponent, BolsaComponent, BolsasComponent, InscricaoComponent, InscricoesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'sebes-web';
}
