export enum StatusEtapa {
  CRIADA = 'CRIADA',
  EM_ANDAMENTO = 'EM_ANDAMENTO',
  ENCERRADA = 'ENCERRADA',
}

export const StatusEtapaDescricao: Record<StatusEtapa, string> = {
  [StatusEtapa.CRIADA]: 'Criada',
  [StatusEtapa.EM_ANDAMENTO]: 'Em andamento',
  [StatusEtapa.ENCERRADA]: 'Encerrada',
};
