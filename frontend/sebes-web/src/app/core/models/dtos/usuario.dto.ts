export interface UsuarioDto {
  id: string | null;
  nomeUsuario: string;
  email: string;
  emailRecuperacao: string | null;
  tipo: string;
}

export class UsuarioRequest {
  nomeUsuario: string;
  email: string;
  emailRecuperacao: string | null;
  tipo: string;

  constructor(dto: UsuarioDto) {
    this.nomeUsuario = dto.nomeUsuario;
    this.email = dto.email;
    this.emailRecuperacao = dto.emailRecuperacao;
    this.tipo = dto.tipo;
  }
}

