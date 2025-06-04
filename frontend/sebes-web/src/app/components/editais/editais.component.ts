import { Component } from '@angular/core';

import { NavbarComponent } from '../shared/navbar/navbar.component';
import { DocumentosComponent } from "./documentos/documentos.component";
import { SidebarDiscenteComponent } from "../shared/sidebar/sidebar-discente/sidebar-discente.component";


@Component({
  selector: 'app-editais',
  imports: [NavbarComponent, DocumentosComponent, SidebarDiscenteComponent],
  templateUrl: './editais.component.html',
  styleUrl: './editais.component.css'
})
export class EditaisComponent {

}
