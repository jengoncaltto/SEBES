import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarDiscenteComponent } from './side-section.component';

describe('SidebarDiscenteComponent', () => {
  let component: SidebarDiscenteComponent;
  let fixture: ComponentFixture<SidebarDiscenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SidebarDiscenteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarDiscenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
