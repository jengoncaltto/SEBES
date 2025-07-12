
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BolsaDto } from '@models/dtos/bolsa.dto';

@Injectable({
  providedIn: 'root'
})
export class BolsaService {
  private apiUrl = 'http://localhost:8080/bolsas';

  constructor(private http: HttpClient) {}

  getAll(): Observable<BolsaDto[]> {
    return this.http.get<BolsaDto[]>(this.apiUrl);
  }

  getById(id: number): Observable<BolsaDto> {
    return this.http.get<BolsaDto>(`${this.apiUrl}/${id}`);
  }

  create(bolsa: BolsaDto): Observable<BolsaDto> {
    return this.http.post<BolsaDto>(this.apiUrl, bolsa);
  }

  update(id: number, bolsa: BolsaDto): Observable<BolsaDto> {
    return this.http.put<BolsaDto>(`${this.apiUrl}/${id}`, bolsa);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

