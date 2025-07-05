export enum StatusRespostaFormulario {
  CRIADA = 'CRIADA',
  ENVIADA = 'ENVIADA'
}

export const StatusRespostaFormularioDescricao: Record<StatusRespostaFormulario, string> = {
  [StatusRespostaFormulario.CRIADA]: 'Criada',
  [StatusRespostaFormulario.ENVIADA]: 'Enviada'
};
