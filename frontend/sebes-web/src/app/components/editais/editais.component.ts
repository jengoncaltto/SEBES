import { Component } from '@angular/core';
import { NavbarComponent } from '../shared/navbar/navbar.component';
import { DocumentosComponent } from "./documentos/documentos.component";



@Component({
  selector: 'app-editais',
  imports: [DocumentosComponent],
  templateUrl: './editais.component.html',
  styleUrl: './editais.component.css'
})
export class EditaisComponent {

}
