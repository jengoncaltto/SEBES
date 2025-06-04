import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelecaoBolsaComponent } from './selecao-bolsa.component';

describe('SelecaoBolsaComponent', () => {
  let component: SelecaoBolsaComponent;
  let fixture: ComponentFixture<SelecaoBolsaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelecaoBolsaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelecaoBolsaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
