import { Component } from '@angular/core';
import { NavbarComponent } from './componentes-gerais/navbar/Navbar.component';
import { EditalComponent } from './bolsa/bolsa.component';

@Component({
  selector: 'app-editais',
  imports: [NavbarComponent, EditalComponent],
  templateUrl: './editais.component.html',
  styleUrl: './editais.component.css'
})
export class EditaisComponent {

}
