import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProcessoSeletivoDto } from '@models/dtos/processo-seletivo.dto';

@Injectable({
  providedIn: 'root'
})
export class ProcessoSeletivoService {

  private apiUrl = 'http://localhost:8080/processos-seletivos'; // ajuste conforme sua API


  constructor(private http: HttpClient) {}

  criar(processo: ProcessoSeletivoDto): Observable<ProcessoSeletivoDto> {
    return this.http.post<ProcessoSeletivoDto>(this.apiUrl, processo);
  }

  buscarPorId(id: string): Observable<ProcessoSeletivoDto> {
    return this.http.get<ProcessoSeletivoDto>(`${this.apiUrl}/${id}`);
  }

  listarTodos(): Observable<ProcessoSeletivoDto[]> {
    return this.http.get<ProcessoSeletivoDto[]>(this.apiUrl);
  }

  buscarPorStatus(status: string): Observable<ProcessoSeletivoDto[]> {
    return this.http.get<ProcessoSeletivoDto[]>(`${this.apiUrl}/status/${status}`);
  }

  buscarPorBolsa(idBolsa: number): Observable<ProcessoSeletivoDto[]> {
    return this.http.get<ProcessoSeletivoDto[]>(`${this.apiUrl}/bolsa/${idBolsa}`);
  }

  atualizarParcial(id: string, dados: Partial<ProcessoSeletivoDto>): Observable<ProcessoSeletivoDto> {
    return this.http.patch<ProcessoSeletivoDto>(`${this.apiUrl}/${id}`, dados);
  }

  deletar(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
