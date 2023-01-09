import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormKontaktPodaciComponent } from './form-kontakt-podaci.component';

describe('FormKontaktPodaciComponent', () => {
  let component: FormKontaktPodaciComponent;
  let fixture: ComponentFixture<FormKontaktPodaciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormKontaktPodaciComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormKontaktPodaciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
