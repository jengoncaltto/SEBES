export enum StatusProcessoSeletivo {
  CRIADO = 'CRIADO',
  EM_ANDAMENTO = 'EM_ANDAMENTO',
  ENCERRADO = 'ENCERRADO',
  SUSPENSO = 'SUSPENSO',
}

export const StatusProcessoSeletivoDescricao: Record<StatusProcessoSeletivo, string> = {
  [StatusProcessoSeletivo.CRIADO]: 'Criado',
  [StatusProcessoSeletivo.EM_ANDAMENTO]: 'Em andamento',
  [StatusProcessoSeletivo.ENCERRADO]: 'Encerrado',
  [StatusProcessoSeletivo.SUSPENSO]: 'Suspenso',
};
