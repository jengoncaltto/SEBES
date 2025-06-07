import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AnexosProcessoSeletivo } from './anexos-processo-seletivo';

describe('AnexosProcessoSeletivo', () => {
  let component: AnexosProcessoSeletivo;
  let fixture: ComponentFixture<AnexosProcessoSeletivo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnexosProcessoSeletivo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnexosProcessoSeletivo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
