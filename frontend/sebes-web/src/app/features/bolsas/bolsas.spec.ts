import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bolsas } from './bolsas';

describe('Bolsas', () => {
  let component: Bolsas;
  let fixture: ComponentFixture<Bolsas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bolsas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Bolsas);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
