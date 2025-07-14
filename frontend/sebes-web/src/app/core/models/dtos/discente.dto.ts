export interface DiscenteDto {
  id: string | null;
  nome: string;
  nomeSocial: string | null;
  telefone: string;
  matricula: string;
  idUsuario: string | null;
}

export class DiscenteRequest {
  nome: string;
  nomeSocial: string | null;
  telefone: string;
  matricula: string;
  idUsuario: string;

  constructor(dto: DiscenteDto) {
    this.nome = dto.nome;
    this.nomeSocial = dto.nomeSocial;
    this.telefone = dto.telefone;
    this.matricula = dto.matricula;
    this.idUsuario = dto.idUsuario ?? ''; // ajuste para null se necessário
  }
}
