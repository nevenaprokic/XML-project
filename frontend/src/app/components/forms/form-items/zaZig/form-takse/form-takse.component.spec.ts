import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTakseComponent } from './form-takse.component';

describe('FormTakseComponent', () => {
  let component: FormTakseComponent;
  let fixture: ComponentFixture<FormTakseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormTakseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormTakseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
