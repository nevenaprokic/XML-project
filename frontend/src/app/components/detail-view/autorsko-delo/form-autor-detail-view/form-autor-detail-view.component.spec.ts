import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAutorDetailViewComponent } from './form-autor-detail-view.component';

describe('FormAutorDetailViewComponent', () => {
  let component: FormAutorDetailViewComponent;
  let fixture: ComponentFixture<FormAutorDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormAutorDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAutorDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
