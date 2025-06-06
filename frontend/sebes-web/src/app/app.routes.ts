import { Routes } from '@angular/router';

// verificação de acesso

// páginas
import { Login } from './components/login/login';
import { Cadastro } from './components/cadastro/cadastro';
import { ProcessosSeletivos } from './components/processoSeletivo/processos-seletivos/processos-seletivos';
import { Bolsas } from './components/bolsas/bolsas';
import { Bolsa } from './components/bolsas/bolsa/bolsa';

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
		component: Bolsas,
		children:[
			{
				path: "bolsa",
				component: Bolsa
			}
		]
	}
];
