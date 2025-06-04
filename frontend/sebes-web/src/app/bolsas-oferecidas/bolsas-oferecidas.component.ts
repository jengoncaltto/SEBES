import { Component } from '@angular/core';
import { HeaderComponent } from '../app-principal/componentes-gerais/header/header.component';
import { BolsaComponent } from './bolsa/bolsa.component';

@Component({
  selector: 'app-bolsas-oferecidas',
  imports: [HeaderComponent, BolsaComponent],
  templateUrl: './bolsas-oferecidas.component.html',
  styleUrl: './bolsas-oferecidas.component.css'
})
export class BolsasOferecidasComponent {

}
