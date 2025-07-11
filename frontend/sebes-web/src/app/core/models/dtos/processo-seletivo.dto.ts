export interface ProcessoSeletivoDto {
  dataInicio: string; // LocalDateTime → ISO string (ex: "2025-07-04T12:00:00")
  dataEncerramento: string;
  status: string;
  idBolsa: number;
}
