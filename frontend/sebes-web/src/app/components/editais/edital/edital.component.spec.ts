import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalComponent } from './bolsa.component';

describe('EditalComponent', () => {
  let component: EditalComponent;
  let fixture: ComponentFixture<EditalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
