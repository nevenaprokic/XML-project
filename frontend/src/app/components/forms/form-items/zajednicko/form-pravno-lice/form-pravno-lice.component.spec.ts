import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPravnoLiceComponent } from './form-pravno-lice.component';

describe('FormPravnoLiceComponent', () => {
  let component: FormPravnoLiceComponent;
  let fixture: ComponentFixture<FormPravnoLiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPravnoLiceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPravnoLiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
