import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { BolsaDTO } from '@models/bolsa.dto';
import { BolsaService } from '@services/bolsa.service';

@Component({
  selector: 'app-bolsas',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './bolsas.html',
  styleUrl: './bolsas.css'
})
export class Bolsas implements OnInit {

  bolsas: BolsaDTO[] = [];

  constructor(private bolsaService: BolsaService) {}

  ngOnInit(): void {
    console.log('teste', this.bolsas);
    this.bolsaService.listarTodas().subscribe({
      next: (dados: any) => this.bolsas = dados,
      error: (err: any) => console.error('Erro ao carregar bolsas:', err)
    });
    console.log('teste', this.bolsas);
  }
}
