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

  solicitarAcesso(email: string): void {
    this.http.post<void>(Endpoints.AUTH, { email }).subscribe({
      next: () => console.log('Código enviado com sucesso'),
      error: (err) => console.error('Erro ao enviar código:', err)
    });
  }

  validarAcesso(email: string, codigo: string): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${Endpoints.AUTH}/verificar-codigo`, { email: email, codigo: codigo });
  }

  getUserLoggedIn(): Observable<UsuarioDto> {
    return this.http.post<any>(Endpoints.AUTH, { token: this.getToken() });
  }
  
  getTipo(): string | null {
   const token = this.getToken();
    if (token == null) return null;

    try {
      const decoded: any = jwtDecode(token);
      return decoded.tipo || null;
    } catch (err) {
      console.error('Erro ao decodificar token para obter tipo:', err);
      return null;
    }
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
