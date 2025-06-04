import { Routes } from '@angular/router';
import { HeaderComponent } from './componentes-gerais/navbar/navbar.component';

// páginas
import { LoginComponent } from './login/login.component';
import { CadastroAlunoComponent } from './cadastro/cadastro-aluno/cadastro-aluno.component';
import { EditaisComponent } from './editais/editais.component';
import { EditalComponent } from './editais/edital/edital.component';
import { BolsasComponent } from './bolsas/bolsas.component';
import { BolsaComponent } from './bolsas/bolsa/bolsa.component';

export const routes: Routes = [
    {
		path: "",
		component: EditaisComponent // fazer uma home, se não toda vez que acessar a pagina vai para os editais
	},
	{
		path: "login"
		component: LoginComponent
	},
	{
		path: "cadastroAluno"
		component: CadastroAlunoComponent
	},
	{
		path: "editais"
		component: EditaisComponent
		children:[
			{
				path: "edital"
				component: EditalComponent
			}
		]
	},
	{
		path: "bolsas"
		component: BolsasComponent
		children:[
			{
				path: "bolsa"
				component: BolsaComponent
			}
		]
	}
];
