import { Component } from '@angular/core';
import { NavbarComponent } from "../shared/navbar/navbar.component";
import { DivBolsaComponent } from "./div-bolsa/div-bolsa.component";
import { SidebarDiscenteComponent } from "../shared/sidebar/sidebar-discente/sidebar-discente.component";

@Component({
  selector: 'app-bolsas',
  imports: [NavbarComponent, DivBolsaComponent, SidebarDiscenteComponent],
  templateUrl: './bolsas.component.html',
  styleUrl: './bolsas.component.css'
})
export class BolsasComponent {

}
