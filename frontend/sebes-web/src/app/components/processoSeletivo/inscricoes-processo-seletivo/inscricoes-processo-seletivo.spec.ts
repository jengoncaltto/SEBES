import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscricoesProcessoSeletivo } from './inscricoes-processo-seletivo';

describe('InscricoesProcessoSeletivo', () => {
  let component: InscricoesProcessoSeletivo;
  let fixture: ComponentFixture<InscricoesProcessoSeletivo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InscricoesProcessoSeletivo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InscricoesProcessoSeletivo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
