import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DivBolsaComponent } from './div-bolsa.component';

describe('DivBolsaComponent', () => {
  let component: DivBolsaComponent;
  let fixture: ComponentFixture<DivBolsaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DivBolsaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DivBolsaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
