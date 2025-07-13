import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProcessoSeletivoDto } from '@models/dtos/processo-seletivo.dto';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root'
})
export class ProcessoSeletivoService {

  constructor(private http: HttpClient) {}

  criar(processo: ProcessoSeletivoDto): Observable<ProcessoSeletivoDto> {
    return this.http.post<ProcessoSeletivoDto>(Endpoints.PROCESSOS, processo);
  }

  buscarPorId(id: string): Observable<ProcessoSeletivoDto> {
    return this.http.get<ProcessoSeletivoDto>(`${Endpoints.PROCESSOS}/${id}`);
  }

  listarTodos(): Observable<ProcessoSeletivoDto[]> {
    return this.http.get<ProcessoSeletivoDto[]>(Endpoints.PROCESSOS);
  }

  buscarPorStatus(status: string): Observable<ProcessoSeletivoDto[]> {
    return this.http.get<ProcessoSeletivoDto[]>(`${Endpoints.PROCESSOS}/status/${status}`);
  }

  buscarPorBolsa(idBolsa: number): Observable<ProcessoSeletivoDto[]> {
    return this.http.get<ProcessoSeletivoDto[]>(`${Endpoints.PROCESSOS}/bolsa/${idBolsa}`);
  }

  atualizarParcial(id: string, dados: Partial<ProcessoSeletivoDto>): Observable<ProcessoSeletivoDto> {
    return this.http.patch<ProcessoSeletivoDto>(`${Endpoints.PROCESSOS}/${id}`, dados);
  }

  deletar(id: string): Observable<void> {
    return this.http.delete<void>(`${Endpoints.PROCESSOS}/${id}`);
  }
}
