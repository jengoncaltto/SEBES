import { Routes } from '@angular/router';

// verificação de acesso

// páginas
import { Login } from './pages/login/login';
import { Cadastro } from './pages/cadastro/cadastro';
import { ProcessosSeletivos } from './pages/processoSeletivo/processos-seletivos/processos-seletivos';
import { Bolsas } from './pages/bolsas/bolsas';
import { Bolsa } from './pages/bolsas/bolsa/bolsa';

export const routes: Routes = [
    {
		path: "",
		component: ProcessosSeletivos // fazer uma home, se não toda vez que acessar a pagina vai para os processosSeletivos
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
	}
];
