import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EtapaDto } from '@models/dtos/etapa.dto';
import { Observable } from 'rxjs';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root'
})
export class EtapaService {

  constructor(private http: HttpClient) {}

  criar(etapa: EtapaDto): Observable<EtapaDto> {
    return this.http.post<EtapaDto>(Endpoints.ETAPAS, etapa);
  }

  buscarPorId(id: string): Observable<EtapaDto> {
    return this.http.get<EtapaDto>(`${Endpoints.ETAPAS}/${id}`);
  }

  atualizarParcial(id: string, etapa: Partial<EtapaDto>): Observable<EtapaDto> {
    return this.http.patch<EtapaDto>(`${Endpoints.ETAPAS}/${id}`, etapa);
  }

  deletar(id: string): Observable<void> {
    return this.http.delete<void>(`${Endpoints.ETAPAS}/${id}`);
  }

  listarTodasDoProcesso(idProcesso: string): Observable<EtapaDto[]> {
    return this.http.get<EtapaDto[]>(`${Endpoints.ETAPAS}/processo/${idProcesso}`);
  }
}
