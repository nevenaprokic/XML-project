import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentDetailViewComponent } from './patent-detail-view.component';

describe('PatentDetailViewComponent', () => {
  let component: PatentDetailViewComponent;
  let fixture: ComponentFixture<PatentDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
