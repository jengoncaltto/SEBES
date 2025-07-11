export enum StatusRecurso {
  ENVIADO = 'ENVIADO',
  INDEFERIDA = 'INDEFERIDA',
  DEFERIDA = 'DEFERIDA'
}

export const StatusRecursoDescricao: Record<StatusRecurso, string> = {
  [StatusRecurso.ENVIADO]: 'Enviado',
  [StatusRecurso.INDEFERIDA]: 'Indeferida',
  [StatusRecurso.DEFERIDA]: 'Deferida'
};
