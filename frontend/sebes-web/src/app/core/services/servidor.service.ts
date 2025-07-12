import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServidorPraeDto } from '@models/dtos/servidor-prae.dto';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root'
})
export class ServidorPraeService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<ServidorPraeDto[]> {
    return this.http.get<ServidorPraeDto[]>(Endpoints.SERVIDORES);
  }

  getById(id: string): Observable<ServidorPraeDto> {
    return this.http.get<ServidorPraeDto>(`${Endpoints.SERVIDORES}/${id}`);
  }

  create(servidor: ServidorPraeDto): Observable<ServidorPraeDto> {
    return this.http.post<ServidorPraeDto>(Endpoints.SERVIDORES, servidor);
  }

  updatePartial(id: string, updates: Partial<ServidorPraeDto>): Observable<ServidorPraeDto> {
    return this.http.patch<ServidorPraeDto>(`${Endpoints.SERVIDORES}/${id}`, updates);
  }

  delete(id: string): Observable<string> {
    return this.http.delete(`${Endpoints.SERVIDORES}/${id}`, { responseType: 'text' });
  }

  getUsuario(idUsuario: string): Observable<any> {
    return this.http.get<any>(`${Endpoints.SERVIDORES}/usuario/${idUsuario}`);
  }
}
