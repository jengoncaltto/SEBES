export interface RespostaFormularioDto {
  id: string;
  dataEnvio: string; // LocalDateTime → ISO string (ex: "2025-07-04T12:00:00")
  tipoForms: string;
  conteudo: string;
  status: string;
  idRespostaAssociada: string;
  idUsuario: string;
  idProcessoSeletivo: string;
}
