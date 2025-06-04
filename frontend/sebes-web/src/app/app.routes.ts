import { Routes } from '@angular/router';

// páginas
import { LoginComponent } from './components/login/login.component';
import { CadastroAlunoComponent } from './components/cadastro/cadastro-aluno/cadastro-aluno.component';
import { EditaisComponent } from './components/editais/editais.component';
import { EditalComponent } from './components/editais/edital/edital.component';
import { BolsasComponent } from './components/bolsas/bolsas.component';
import { BolsaComponent } from './components/bolsas/bolsa/bolsa.component';

export const routes: Routes = [
    {
		path: "",
		component: EditaisComponent // fazer uma home, se não toda vez que acessar a pagina vai para os editais
	},
	{
		path: "login",
		component: LoginComponent
	},
	{
		path: "cadastroAluno",
		component: CadastroAlunoComponent
	},
	{
		path: "editais",
		component: EditaisComponent,
		children:[
			{
				path: "edital",
				component: EditalComponent
			}
		]
	},
	{
		path: "bolsas",
		component: BolsasComponent,
		children:[
			{
				path: "bolsa",
				component: BolsaComponent
			}
		]
	}
];
