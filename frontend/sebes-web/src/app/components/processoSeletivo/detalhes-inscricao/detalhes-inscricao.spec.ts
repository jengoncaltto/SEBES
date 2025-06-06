import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalhesInscricao } from './detalhesInscricao';

describe('DetalhesInscricao', () => {
  let component: DetalhesInscricao;
  let fixture: ComponentFixture<DetalhesInscricao>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalhesInscricao]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalhesInscricao);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
