import { Component } from '@angular/core';
import { NavbarComponent } from "../../shared/navbar/navbar.component";
import { SidebarDiscenteComponent } from "../../shared/sidebar/sidebar-discente/sidebar-discente.component";

@Component({
  selector: 'app-bolsa',
  imports: [NavbarComponent, SidebarDiscenteComponent],
  templateUrl: './bolsa.component.html',
  styleUrl: './bolsa.component.css'
})
export class BolsaComponent {

}
