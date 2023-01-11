import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormZnakComponent } from './form-znak.component';

describe('FormZnakComponent', () => {
  let component: FormZnakComponent;
  let fixture: ComponentFixture<FormZnakComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormZnakComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormZnakComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
