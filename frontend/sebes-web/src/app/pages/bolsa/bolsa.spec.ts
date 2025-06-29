import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bolsa } from './bolsa';

describe('Bolsa', () => {
  let component: Bolsa;
  let fixture: ComponentFixture<Bolsa>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bolsa]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Bolsa);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
