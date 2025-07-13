import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DiscenteDto } from '@models/dtos/discente.dto';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root'
})
export class DiscenteService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<DiscenteDto[]> {
    return this.http.get<DiscenteDto[]>(Endpoints.DISCENTES);
  }

  getById(id: string): Observable<DiscenteDto> {
    return this.http.get<DiscenteDto>(`${Endpoints.DISCENTES}/${id}`);
  }

  create(discente: DiscenteDto): Observable<DiscenteDto> {
    return this.http.post<DiscenteDto>(Endpoints.DISCENTES, discente);
  }

  updatePartial(id: string, updates: Partial<DiscenteDto>): Observable<DiscenteDto> {
    return this.http.patch<DiscenteDto>(`${Endpoints.DISCENTES}/${id}`, updates);
  }

  delete(id: string): Observable<string> {
    return this.http.delete(`${Endpoints.DISCENTES}/${id}`, { responseType: 'text' });
  }

  getUsuario(idUsuario: string): Observable<any> {
    return this.http.get<any>(`${Endpoints.DISCENTES}/usuario/${idUsuario}`);
  }
}
