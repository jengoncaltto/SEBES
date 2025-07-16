import { UsuarioTipo } from '@models/enums/usuario.roles';
import { AllPages } from '@pages/index';
import { Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';

export const routes: Routes = [
	{
		path: "acesso-negado",
		component: AllPages.AcessoNegado
	},
    {
		path: "",
		component: AllPages.PaginaInicial 
	},
	{
		path: "inicio",
		component: AllPages.PaginaInicial 
	},
	{
		path: "login",
  		canActivate: [AuthGuard],
		data: { loggedOut: true },
		component: AllPages.Login
	},
	{
		path: "cadastro",
  		canActivate: [AuthGuard],
		data: { loggedOut: true },
		component: AllPages.Cadastro
	},
	{
		path: "processos-seletivos",
  		canActivate: [AuthGuard],
		data: { requireLogin: true },
		component: AllPages.ProcessosSeletivos
	},
	{
		path: "bolsas",
  		canActivate: [AuthGuard],
		data: { requireLogin: true },
		component: AllPages.Bolsas
	},
	{
		path: "perfil",
  		canActivate: [AuthGuard],
		data: { requireLogin: true },
		component: AllPages.MeuPerfilComponent
	},
	{
		path: "config",
  		canActivate: [AuthGuard],
		data: { requireLogin: true },
		component: AllPages.PaginaInicial
	},
	{
		path: "detalhes",
  		canActivate: [AuthGuard],
		data: { requireLogin: true, roles: [UsuarioTipo.DISCENTE] },
		component: AllPages.DetalhesInscricao
	},
	{
		path: "inscricoesProcesso",
		component: AllPages.InscricoesProcessoSeletivo,
	},
	{
		path: "anexosProcesso",
		component: AllPages.AnexosProcessoSeletivo
	},
];
