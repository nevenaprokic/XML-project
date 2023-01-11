import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormZigComponent } from './form-zig.component';

describe('FormZigComponent', () => {
  let component: FormZigComponent;
  let fixture: ComponentFixture<FormZigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormZigComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormZigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
