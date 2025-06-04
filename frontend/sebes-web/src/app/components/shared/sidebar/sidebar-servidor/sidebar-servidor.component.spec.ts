import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarServidor } from './side-section-funcionario.component';

describe('SidebarServidor', () => {
  let component: SidebarServidor;
  let fixture: ComponentFixture<SidebarServidor>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SidebarServidor]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarServidor);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
