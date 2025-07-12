import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioDto } from '@models/dtos/usuario.dto';
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

  create(usuario: UsuarioDto): Observable<UsuarioDto> {
    return this.http.post<UsuarioDto>(Endpoints.USUARIOS, usuario);
  }

  updatePartial(id: string, updates: Partial<UsuarioDto>): Observable<UsuarioDto> {
    return this.http.patch<UsuarioDto>(`${Endpoints.USUARIOS}/${id}`, updates);
  }

  delete(id: string): Observable<void | string> {
    return this.http.delete(`${Endpoints.USUARIOS}/${id}`, { responseType: 'text' });
  }
}
