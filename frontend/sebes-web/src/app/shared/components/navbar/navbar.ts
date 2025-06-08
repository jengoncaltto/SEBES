import { Component } from '@angular/core';
import { filter } from 'rxjs/operators';
import { Router, RouterModule, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [ RouterModule ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {
 
}
