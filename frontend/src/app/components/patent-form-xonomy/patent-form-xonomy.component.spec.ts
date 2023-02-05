import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentFormXonomyComponent } from './patent-form-xonomy.component';

describe('PatentFormXonomyComponent', () => {
  let component: PatentFormXonomyComponent;
  let fixture: ComponentFixture<PatentFormXonomyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentFormXonomyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentFormXonomyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
