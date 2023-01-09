import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPatentComponent } from './form-patent.component';

describe('FormPatentComponent', () => {
  let component: FormPatentComponent;
  let fixture: ComponentFixture<FormPatentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPatentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPatentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
