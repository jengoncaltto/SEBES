import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PublicacaoDto } from '@models/dtos/publicacao.dto';
import { Observable } from 'rxjs';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root'
})
export class PublicacaoService {

  constructor(private http: HttpClient) {}

  criar(publicacao: PublicacaoDto): Observable<PublicacaoDto> {
    return this.http.post<PublicacaoDto>(Endpoints.PUBLICACOES, publicacao);
  }

  listarTodas(): Observable<PublicacaoDto[]> {
    return this.http.get<PublicacaoDto[]>(Endpoints.PUBLICACOES);
  }

  buscarPorId(id: number): Observable<PublicacaoDto> {
    return this.http.get<PublicacaoDto>(`${Endpoints.PUBLICACOES}/${id}`);
  }

  atualizarParcial(id: number, dados: Partial<PublicacaoDto>): Observable<PublicacaoDto> {
    return this.http.patch<PublicacaoDto>(`${Endpoints.PUBLICACOES}/${id}`, dados);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${Endpoints.PUBLICACOES}/${id}`);
  }
}
