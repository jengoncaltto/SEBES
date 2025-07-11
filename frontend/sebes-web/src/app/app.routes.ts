import { Routes } from '@angular/router';

// verificação de acesso

// páginas
import { PaginaInicial } from './pages/pagina-inicial/pagina-inicial';
import { Login } from './pages/login/login';
import { Cadastro } from './pages/cadastro/cadastro';
import { ProcessosSeletivos } from './pages/processoSeletivo/processos-seletivos/processos-seletivos';
import { Bolsas } from './pages/bolsas/bolsas';
import { Bolsa } from './pages/bolsas/bolsa/bolsa';

export const routes: Routes = [
    {
		path: "",
		component: PaginaInicial 
	},
	{
		/* Página Inicial */
		path: "inicio",
		component: PaginaInicial 
	},
	{
		/* Página do Login */
		path: "login",
		component: Login
	},
	{
		/* Página do Cadastro */
		path: "cadastro",
		component: Cadastro
	},
	{
		/* Publicações */
		path: "processosSeletivos",
		component: ProcessosSeletivos
	},
	{
		/* Mostra todas as bolsas oferecidas */
		path: "bolsas",
		component: Bolsas
	},
	{
		/* Detalhes de um Processo seletivo para uma bolsa */
		path: "bolsas/bolsa",
		component: Bolsa
	},
	{
		/* Perfil do Usuário */
		path: "meuPerfil",
		component: PaginaInicial 
		/* Mudar quando for criada a página */
	},
	{
		/* Configurações */
		path: "config",
		component: PaginaInicial 
		/* Mudar quando for criada a página */
	}
];
