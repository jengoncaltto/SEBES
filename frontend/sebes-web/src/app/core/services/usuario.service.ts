import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioDto, UsuarioRequest } from '@models/dtos/usuario.dto';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<UsuarioDto[]> {
    return this.http.get<UsuarioDto[]>(Endpoints.USUARIOS);
  }

  getById(id: string): Observable<UsuarioDto> {
    return this.http.get<UsuarioDto>(`${Endpoints.USUARIOS}/${id}`);
  }

  isEmailCadastrado(email: string): Observable<boolean> {
    return this.http.post<boolean>(`${Endpoints.USUARIOS}/is-email-cadastrado`, { email: email });
  }

  create(usuario: UsuarioRequest): Observable<UsuarioDto> {
    return this.http.post<UsuarioDto>(Endpoints.USUARIOS, usuario);
  }

  updatePartial(id: string, updates: Partial<UsuarioDto>): Observable<UsuarioDto> {
    return this.http.patch<UsuarioDto>(`${Endpoints.USUARIOS}/${id}`, updates);
  }

  delete(id: string): Observable<void | string> {
    return this.http.delete(`${Endpoints.USUARIOS}/${id}`, { responseType: 'text' });
  }
}
