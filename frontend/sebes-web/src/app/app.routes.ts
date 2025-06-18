import { Routes } from '@angular/router';

// verificação de acesso

// páginas
import { PaginaInicial } from './pages/pagina-inicial/pagina-inicial';
import { Login } from './pages/login/login';
import { Cadastro } from './pages/cadastro/cadastro';
import { ProcessosSeletivos } from './pages/processoSeletivo/processos-seletivos/processos-seletivos';
import { Bolsas } from './pages/bolsas/bolsas';
import { Bolsa } from './pages/bolsas/bolsa/bolsa';
import { DetalhesInscricao } from './pages/processoSeletivo/detalhes-inscricao/detalhes-inscricao';
import { InscricoesProcessoSeletivo } from './pages/processoSeletivo/inscricoes-processo-seletivo/inscricoes-processo-seletivo';

export const routes: Routes = [
    {
		/* Página Inicial */
		path: "",
		component: PaginaInicial 
	},
	{
		path: "inicio",
		component: PaginaInicial 
	},
	{
		/* Página de Login */
		path: "login",
		component: Login
	},
	{
		/* Página de Cadastro */
		path: "cadastro",
		component: Cadastro
	},
	{
		/* ??? */
		path: "processosSeletivos",
		component: ProcessosSeletivos
	},
	{
		/* Mostra todos os auxílios oferecidos */
		path: "bolsas",
		component: Bolsas
	},
	{
		/* Página de detalhes de uma processo seletivo para um dos auxílios */
		path: "bolsas/bolsa",
		component: Bolsa
	},
	{
		/* Página de perfil do usuário */
		path: "meuPerfil",
		component: PaginaInicial
		/* Modificar para a página certa quando for criada */
	},
	{
		/* Documentos enviados ou pendentes do estudante  */
		path: "detalhesInscricao",
		component: DetalhesInscricao
	},
	{
		/* Todas as inscrições que foram enviadas para um certo processo seletivo */
		path: "inscricoesProcesso",
		component: InscricoesProcessoSeletivo
	}
];
