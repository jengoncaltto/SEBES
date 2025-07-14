export interface ServidorPraeDto {
  id: string | null;
  nome: string;
  nomeSocial: string;
  telefone: string;
  setor: string;
  cargo: string;
  idUsuario: string | null;
}

export class ServidorRequest {
  nome: string;
  nomeSocial: string;
  telefone: string;
  setor: string;
  cargo: string;
  idUsuario: string;

  constructor(dto: ServidorPraeDto) {
    this.nome = dto.nome;
    this.nomeSocial = dto.nomeSocial;
    this.telefone = dto.telefone;
    this.setor = dto.setor;
    this.cargo = dto.cargo;
    this.idUsuario = dto.idUsuario ?? ''; // pode ajustar para null se preferir
  }
}
