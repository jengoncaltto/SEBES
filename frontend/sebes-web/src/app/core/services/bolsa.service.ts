import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BolsaDTO } from '@models/bolsa.dto';

@Injectable({
  providedIn: 'root'
})
export class BolsaService {

  private readonly apiUrl = 'http://localhost:8080/bolsas';

  constructor(private http: HttpClient) {}

  // POST: Cadastrar nova bolsa
  cadastrarBolsa(bolsa: BolsaDTO): Observable<BolsaDTO> {
    return this.http.post<BolsaDTO>(`${this.apiUrl}/cadastrar`, bolsa);
  }

  // GET: Listar todas
  listarTodas(): Observable<BolsaDTO[]> {
    return this.http.get<BolsaDTO[]>(this.apiUrl);
  }

  // GET: Buscar por ID
  buscarPorId(id: number): Observable<BolsaDTO> {
    return this.http.get<BolsaDTO>(`${this.apiUrl}/${id}`);
  }

  // PATCH: Atualizar parcialmente
  atualizarParcial(id: number, campos: Partial<BolsaDTO>): Observable<BolsaDTO> {
    return this.http.patch<BolsaDTO>(`${this.apiUrl}/${id}`, campos);
  }
}
