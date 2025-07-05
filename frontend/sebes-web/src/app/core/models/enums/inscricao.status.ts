export enum StatusInscricao {
  ENVIADA = 'ENVIADA',
  INDEFERIDA = 'INDEFERIDA',
  DEFERIDA = 'DEFERIDA',
  EM_RECURSO = 'EM_RECURSO'
}

export const StatusInscricaoDescricao: Record<StatusInscricao, string> = {
  [StatusInscricao.ENVIADA]: 'Enviada',
  [StatusInscricao.INDEFERIDA]: 'Indeferida',
  [StatusInscricao.DEFERIDA]: 'Deferida',
  [StatusInscricao.EM_RECURSO]: 'Em recurso'
};
