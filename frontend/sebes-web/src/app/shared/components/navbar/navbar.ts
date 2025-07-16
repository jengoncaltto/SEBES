import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {


  constructor(private authService: AuthService,
              private usuarioService: UsuarioService,
              private router: Router) {}
              
  isLoggedIn = this.authService.isLoggedIn();
}
