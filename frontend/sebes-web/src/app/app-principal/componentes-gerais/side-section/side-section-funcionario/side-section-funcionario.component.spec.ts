import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideSectionFuncionarioComponent } from './side-section-funcionario.component';

describe('SideSectionFuncionarioComponent', () => {
  let component: SideSectionFuncionarioComponent;
  let fixture: ComponentFixture<SideSectionFuncionarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SideSectionFuncionarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SideSectionFuncionarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
