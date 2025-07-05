import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RespostaFormularioDto } from '@models/dtos/resposta-formulario.dto';

@Injectable({
  providedIn: 'root',
})
export class RespostaFormularioService {
  private readonly API = 'http://localhost:8080/respostas';

  constructor(private http: HttpClient) {}

  criar(dto: RespostaFormularioDto): Observable<any> {
    return this.http.post(`${this.API}/criar`, dto);
  }

  buscarPorId(id: string): Observable<RespostaFormularioDto> {
    return this.http.get<RespostaFormularioDto>(`${this.API}/${id}`);
  }

  buscarPorStatus(status: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${this.API}/status/${status}`);
  }

  buscarPorTipoForms(tipoForms: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${this.API}/tipo/${tipoForms}`);
  }

  buscarPorIdUsuario(idUsuario: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${this.API}/usuario/${idUsuario}`);
  }

  buscarPorIdProcessoSeletivo(idProcesso: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${this.API}/processo/${idProcesso}`);
  }

  buscarPorDataEnvio(data: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${this.API}/data/${data}`);
  }

  buscarPorIdRespostaAssociada(idResposta: string): Observable<RespostaFormularioDto[]> {
    return this.http.get<RespostaFormularioDto[]>(`${this.API}/resposta-associada/${idResposta}`);
  }

  atualizarParcial(id: string, updates: any): Observable<RespostaFormularioDto> {
    return this.http.patch<RespostaFormularioDto>(`${this.API}/${id}`, updates);
  }

  deletar(id: string): Observable<any> {
    return this.http.delete(`${this.API}/${id}`);
  }
}
