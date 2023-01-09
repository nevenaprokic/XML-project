import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAdresaComponent } from './form-adresa.component';

describe('FormAdresaComponent', () => {
  let component: FormAdresaComponent;
  let fixture: ComponentFixture<FormAdresaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormAdresaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAdresaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
