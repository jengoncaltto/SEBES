import { Component } from '@angular/core';
import { NavbarComponent } from "../shared/navbar/navbar.component";
import { DivBolsaComponent } from "./div-bolsa/div-bolsa.component";

@Component({
  selector: 'app-bolsas',
  imports: [DivBolsaComponent],
  templateUrl: './bolsas.component.html',
  styleUrl: './bolsas.component.css'
})
export class BolsasComponent {

}
