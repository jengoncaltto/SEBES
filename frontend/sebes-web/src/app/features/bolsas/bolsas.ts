import { Component } from '@angular/core';
import { Navbar } from "../shared/navbar/navbar";
import { DivBolsaComponent } from "./div-bolsa/div-bolsa.component";

@Component({
  selector: 'app-bolsas',
  imports: [DivBolsaComponent],
  templateUrl: './bolsas.html',
  styleUrl: './bolsas.css'
})
export class Bolsas {

}
