import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {

  isLoggedIn = false;
  constructor(private authService: AuthService) {}
              
  isUsuarioLoggedIn(){
    this.isLoggedIn = this.authService.isLoggedIn();
  }
    
}
