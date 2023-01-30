import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakseDetailViewComponent } from './takse-detail-view.component';

describe('TakseDetailViewComponent', () => {
  let component: TakseDetailViewComponent;
  let fixture: ComponentFixture<TakseDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TakseDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TakseDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
