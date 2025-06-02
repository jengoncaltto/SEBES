import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BolsasOferecidasComponent } from './bolsas-oferecidas.component';

describe('BolsasOferecidasComponent', () => {
  let component: BolsasOferecidasComponent;
  let fixture: ComponentFixture<BolsasOferecidasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BolsasOferecidasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BolsasOferecidasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
