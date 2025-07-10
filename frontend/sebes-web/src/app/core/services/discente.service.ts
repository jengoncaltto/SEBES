import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DiscenteDto } from '@models/dtos/discente.dto';

@Injectable({
  providedIn: 'root'
})
export class DiscenteService {
  private apiUrl = 'http://localhost:8080/discentes'; // ajuste conforme sua API real

  constructor(private http: HttpClient) {}

  getAll(): Observable<DiscenteDto[]> {
    return this.http.get<DiscenteDto[]>(this.apiUrl);
  }

  getById(id: string): Observable<DiscenteDto> {
    return this.http.get<DiscenteDto>(`${this.apiUrl}/${id}`);
  }

  create(discente: DiscenteDto): Observable<DiscenteDto> {
    return this.http.post<DiscenteDto>(this.apiUrl, discente);
  }

  updatePartial(id: string, updates: Partial<DiscenteDto>): Observable<DiscenteDto> {
    return this.http.patch<DiscenteDto>(`${this.apiUrl}/${id}`, updates);
  }

  delete(id: string): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }

  getUsuario(idUsuario: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/usuario/${idUsuario}`);
  }
}
