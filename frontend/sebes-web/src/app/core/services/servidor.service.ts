import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServidorPraeDto } from '@models/dtos/servidor-prae.dto';

@Injectable({
  providedIn: 'root'
})
export class ServidorPraeService {

  private apiUrl = 'http://localhost:8080/servidores'; // ajuste conforme sua API real


  constructor(private http: HttpClient) {}

  getAll(): Observable<ServidorPraeDto[]> {
    return this.http.get<ServidorPraeDto[]>(this.apiUrl);
  }

  getById(id: string): Observable<ServidorPraeDto> {
    return this.http.get<ServidorPraeDto>(`${this.apiUrl}/${id}`);
  }

  create(servidor: ServidorPraeDto): Observable<ServidorPraeDto> {
    return this.http.post<ServidorPraeDto>(this.apiUrl, servidor);
  }

  updatePartial(id: string, updates: Partial<ServidorPraeDto>): Observable<ServidorPraeDto> {
    return this.http.patch<ServidorPraeDto>(`${this.apiUrl}/${id}`, updates);
  }

  delete(id: string): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }

  getUsuario(idUsuario: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/usuario/${idUsuario}`);
  }
}
