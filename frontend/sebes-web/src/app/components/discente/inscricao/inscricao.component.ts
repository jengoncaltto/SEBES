import { Component } from '@angular/core';
import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { SidebarDiscenteComponent } from '../../shared/sidebar/sidebar-discente/sidebar-discente.component';


@Component({
  selector: 'app-inscricao',
  imports: [NavbarComponent, SidebarDiscenteComponent],
  templateUrl: './inscricao.component.html',
  styleUrl: './inscricao.component.css'
})
export class InscricaoComponent {

}
