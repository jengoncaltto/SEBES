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
import { AnexosProcessoSeletivo } from './pages/processoSeletivo/anexos-processo-seletivo/anexos-processo-seletivo';

export const routes: Routes = [
    {
		path: "",
		component: PaginaInicial 
	},
	{
		path: "inicio",
		component: PaginaInicial 
	},
	{
		path: "login",
		component: Login
	},
	{
		path: "cadastro",
		component: Cadastro
	},
	{
		path: "processosSeletivos",
		component: ProcessosSeletivos
	},
	{
		path: "bolsas",
		component: Bolsas
	},
	{
		path: "bolsas/bolsa",
		component: Bolsa
	},
	/* Para facilitar testes */
	{
		path: "detalhes",
		component: DetalhesInscricao
	},
	{
		path: "inscricoesProcesso",
		component: InscricoesProcessoSeletivo
	},
	{
		path: "anexosProcesso",
		component: AnexosProcessoSeletivo
	},
	{
		path: "meuperfil",
		component: PaginaInicial
	}
];
