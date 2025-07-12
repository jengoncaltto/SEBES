import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioDto } from '@models/dtos/usuario.dto';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/usuarios'; // Ajuste se necessário


  constructor(private http: HttpClient) {}

  getAll(): Observable<UsuarioDto[]> {
    return this.http.get<UsuarioDto[]>(this.apiUrl);
  }

  getById(id: string): Observable<UsuarioDto> {
    return this.http.get<UsuarioDto>(`${this.apiUrl}/${id}`);
  }

  create(usuario: UsuarioDto): Observable<UsuarioDto> {
    return this.http.post<UsuarioDto>(this.apiUrl, usuario);
  }

  updatePartial(id: string, updates: Partial<UsuarioDto>): Observable<UsuarioDto> {
    return this.http.patch<UsuarioDto>(`${this.apiUrl}/${id}`, updates);
  }

  delete(id: string): Observable<void | string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }
}
