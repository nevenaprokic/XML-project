import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAutorskoDeloComponent } from './form-autorsko-delo.component';

describe('FormAutorskoDeloComponent', () => {
  let component: FormAutorskoDeloComponent;
  let fixture: ComponentFixture<FormAutorskoDeloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormAutorskoDeloComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAutorskoDeloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
