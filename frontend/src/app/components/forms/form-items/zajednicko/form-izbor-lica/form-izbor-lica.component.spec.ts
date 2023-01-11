import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormIzborLicaComponent } from './form-izbor-lica.component';

describe('FormIzborLicaComponent', () => {
  let component: FormIzborLicaComponent;
  let fixture: ComponentFixture<FormIzborLicaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormIzborLicaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormIzborLicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
