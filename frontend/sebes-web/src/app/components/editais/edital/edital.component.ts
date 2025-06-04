import { Component } from '@angular/core';
import { NavbarComponent } from "../../shared/navbar/navbar.component";
import { SidebarDiscenteComponent } from "../../shared/sidebar/sidebar-discente/sidebar-discente.component";

@Component({
  selector: 'app-edital',
  imports: [NavbarComponent, SidebarDiscenteComponent],
  templateUrl: './edital.component.html',
  styleUrl: './edital.component.css'
})
export class EditalComponent {

}
