import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalProcessoSeletivo } from './edital-processo-seletivo';

describe('EditalProcessoSeletivo', () => {
  let component: EditalProcessoSeletivo;
  let fixture: ComponentFixture<EditalProcessoSeletivo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalProcessoSeletivo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditalProcessoSeletivo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
