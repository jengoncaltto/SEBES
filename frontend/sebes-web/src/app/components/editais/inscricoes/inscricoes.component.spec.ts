import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscricoesComponent } from './bolsa.component';

describe('InscricoesComponent', () => {
  let component: InscricoesComponent;
  let fixture: ComponentFixture<InscricoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InscricoesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InscricoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
