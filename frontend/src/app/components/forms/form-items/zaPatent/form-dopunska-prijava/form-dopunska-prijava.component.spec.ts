import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormDopunskaPrijavaComponent } from './form-dopunska-prijava.component';

describe('FormDopunskaPrijavaComponent', () => {
  let component: FormDopunskaPrijavaComponent;
  let fixture: ComponentFixture<FormDopunskaPrijavaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormDopunskaPrijavaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormDopunskaPrijavaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
