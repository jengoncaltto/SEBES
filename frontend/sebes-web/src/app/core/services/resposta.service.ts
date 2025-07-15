import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RespostaFormularioDto } from '@models/dtos/resposta-formulario.dto';
import { Endpoints } from '@models/enums/api.endpoints';

@Injectable({
  providedIn: 'root',
})
export class RespostaFormularioService {

  constructor(private http: HttpClient) {}

  criar(dto: RespostaFormularioDto): Observable<any> {
    return this.http.post(`${Endpoints.RESPOSTAS}/criar`, dto);
  }

  buscarPorId(id: string): Observable<RespostaFormularioDto> {
    return this.http.get<RespostaFormularioDto>(`${Endpoints.RESPOSTAS}/${id}`);
  }

  buscarPorStatus(status: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${Endpoints.RESPOSTAS}/status/${status}`);
  }

  buscarPorTipoForms(tipoForms: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${Endpoints.RESPOSTAS}/tipo/${tipoForms}`);
  }

  buscarPorIdUsuario(idUsuario: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${Endpoints.RESPOSTAS}/usuario/${idUsuario}`);
  }

  buscarPorIdProcessoSeletivo(idProcesso: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${Endpoints.RESPOSTAS}/processo/${idProcesso}`);
  }

  buscarPorDataEnvio(data: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${Endpoints.RESPOSTAS}/data/${data}`);
  }

  buscarPorIdRespostaAssociada(idResposta: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${Endpoints.RESPOSTAS}/resposta-associada/${idResposta}`);
  }

  atualizarParcial(id: string, updates: any): Observable<RespostaFormularioDto> {
    return this.http.patch<RespostaFormularioDto>(`${Endpoints.RESPOSTAS}/${id}`, updates);
  }

  deletar(id: string): Observable<any> {
    return this.http.delete(`${Endpoints.RESPOSTAS}/${id}`);
  }
}
