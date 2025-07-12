
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BolsaDto } from '@models/dtos/bolsa.dto';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root'
})
export class BolsaService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<BolsaDto[]> {
    return this.http.get<BolsaDto[]>(Endpoints.BOLSAS);
  }

  getById(id: number): Observable<BolsaDto> {
    return this.http.get<BolsaDto>(`${Endpoints.BOLSAS}/${id}`);
  }

  create(bolsa: BolsaDto): Observable<BolsaDto> {
    return this.http.post<BolsaDto>(Endpoints.BOLSAS, bolsa);
  }

  update(id: number, bolsa: BolsaDto): Observable<BolsaDto> {
    return this.http.put<BolsaDto>(`${Endpoints.BOLSAS}/${id}`, bolsa);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${Endpoints.BOLSAS}/${id}`);
  }
}

