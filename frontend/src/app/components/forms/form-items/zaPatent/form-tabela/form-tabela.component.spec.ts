import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTabelaComponent } from './form-tabela.component';

describe('FormTabelaComponent', () => {
  let component: FormTabelaComponent;
  let fixture: ComponentFixture<FormTabelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormTabelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormTabelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
