import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProcessosSeletivos } from './processos-seletivos';

describe('ProcessosSeletivos', () => {
  let component: ProcessosSeletivos;
  let fixture: ComponentFixture<ProcessosSeletivos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProcessosSeletivos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProcessosSeletivos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
