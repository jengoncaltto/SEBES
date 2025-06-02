import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaginaBolsaComponent } from './pagina-bolsa.component';

describe('PaginaBolsaComponent', () => {
  let component: PaginaBolsaComponent;
  let fixture: ComponentFixture<PaginaBolsaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaginaBolsaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaginaBolsaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
