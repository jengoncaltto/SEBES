import { Component } from '@angular/core';
import { CadastroAlunoComponent } from '../../cadastro/cadastro-aluno/cadastro-aluno.component';

@Component({
  selector: 'app-login',
  imports: [CadastroAlunoComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

}
