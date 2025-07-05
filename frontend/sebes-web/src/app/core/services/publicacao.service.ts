import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PublicacaoDto } from '@models/dtos/publicacao.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PublicacaoService {
  private apiUrl = 'http://localhost:8080/api/publicacoes'; // ajuste se necessário

  constructor(private http: HttpClient) {}

  criar(publicacao: PublicacaoDto): Observable<PublicacaoDto> {
    return this.http.post<PublicacaoDto>(this.apiUrl, publicacao);
  }

  listarTodas(): Observable<PublicacaoDto[]> {
    return this.http.get<PublicacaoDto[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<PublicacaoDto> {
    return this.http.get<PublicacaoDto>(`${this.apiUrl}/${id}`);
  }

  atualizarParcial(id: number, dados: Partial<PublicacaoDto>): Observable<PublicacaoDto> {
    return this.http.patch<PublicacaoDto>(`${this.apiUrl}/${id}`, dados);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
