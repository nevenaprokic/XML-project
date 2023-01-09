import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormFizickoLiceComponent } from './form-fizicko-lice.component';

describe('FormFizickoLiceComponent', () => {
  let component: FormFizickoLiceComponent;
  let fixture: ComponentFixture<FormFizickoLiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormFizickoLiceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormFizickoLiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
