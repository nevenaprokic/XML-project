import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormResenjeComponent } from './form-resenje.component';

describe('FormResenjeComponent', () => {
  let component: FormResenjeComponent;
  let fixture: ComponentFixture<FormResenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormResenjeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormResenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
