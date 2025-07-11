export interface PublicacaoDto {
  id: number;
  dataPublicacao: string; // LocalDateTime → string ISO (ex: "2025-07-04T13:45:00")
  conteudo: string;
  idUsuario: string;
}
