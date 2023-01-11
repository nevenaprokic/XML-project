import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPronalazakComponent } from './form-pronalazak.component';

describe('FormPronalazakComponent', () => {
  let component: FormPronalazakComponent;
  let fixture: ComponentFixture<FormPronalazakComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPronalazakComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPronalazakComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
