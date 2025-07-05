import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EtapaDto } from '@models/dtos/etapa.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EtapaService {
  private apiUrl = 'http://localhost:8080/api/etapas'; // ajuste para a URL real da sua API

  constructor(private http: HttpClient) {}

  criar(etapa: EtapaDto): Observable<EtapaDto> {
    return this.http.post<EtapaDto>(this.apiUrl, etapa);
  }

  buscarPorId(id: string): Observable<EtapaDto> {
    return this.http.get<EtapaDto>(`${this.apiUrl}/${id}`);
  }

  atualizarParcial(id: string, etapa: Partial<EtapaDto>): Observable<EtapaDto> {
    return this.http.patch<EtapaDto>(`${this.apiUrl}/${id}`, etapa);
  }

  deletar(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  listarTodasDoProcesso(idProcesso: string): Observable<EtapaDto[]> {
    return this.http.get<EtapaDto[]>(`${this.apiUrl}/processo/${idProcesso}`);
  }
}
