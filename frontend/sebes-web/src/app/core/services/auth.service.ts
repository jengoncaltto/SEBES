import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { Endpoints } from '@models/enums/api.endpoints';
import { UsuarioDto } from '@models/dtos/usuario.dto';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private tokenKey = 'token';

  constructor(private http: HttpClient) {}

  solicitarAcesso(email: string): void{
    this.http.post<any>(Endpoints.AUTH, { email: email });
  }

  validarAcesso(email: string, codigo: string): void {
    this.http.post<{ token: string }>(`${Endpoints.AUTH}/verificar-codigo`, 
      { email: email, codigo: codigo }).subscribe({
      next: (response) => {
        const token = response.token;
        localStorage.setItem(this.tokenKey, token);
      },
      error: (err) => {
        console.error('Erro ao validar código:', err);
      }
    });
  }

  getUserLoggedIn(): Observable<UsuarioDto> {
    return this.http.post<any>(Endpoints.AUTH, { token: this.getToken() });
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    if (token == null ) return false;

    const decoded: any = jwtDecode(token);
    const exp = decoded.exp;

    if (exp == null) return false;

    const now = Math.floor(Date.now() / 1000);
    return exp > now;
  }



  logout() {
    localStorage.removeItem(this.tokenKey);
  }
}
