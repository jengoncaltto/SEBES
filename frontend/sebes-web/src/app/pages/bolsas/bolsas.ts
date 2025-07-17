
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { BolsaDto } from '@models/dtos/bolsa.dto';
import { BolsaService } from '@services/bolsa.service';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-bolsas',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './bolsas.html',
  styleUrl: './bolsas.css',
})
export class Bolsas implements OnInit {
  bolsas: BolsaDto[] = [];
  isLoggedIn = false;

  constructor(private authService: AuthService,
  private bolsaService: BolsaService) {}

  isUsuarioLoggedIn(){
    this.isLoggedIn = this.authService.isLoggedIn();
  }
  
  ngOnInit(): void {
    this.carregarBolsas();
    this.isUsuarioLoggedIn();
  }

  private carregarBolsas(): void {
    this.bolsaService.getAll().subscribe({
      next: (dados: BolsaDto[]) => {
        this.bolsas = dados;
        console.log(dados);
      },
      error: (err : any)  => {
        console.error('Erro ao carregar bolsas:', err);
      },
    });
  }
}

