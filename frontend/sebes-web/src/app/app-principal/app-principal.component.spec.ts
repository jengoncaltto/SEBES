import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppPrincipalComponent } from './app-principal.component';

describe('AppPrincipalComponent', () => {
  let component: AppPrincipalComponent;
  let fixture: ComponentFixture<AppPrincipalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppPrincipalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppPrincipalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
