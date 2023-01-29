import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RanijePrijaveDetailViewComponent } from './ranije-prijave-detail-view.component';

describe('RanijePrijaveDetailViewComponent', () => {
  let component: RanijePrijaveDetailViewComponent;
  let fixture: ComponentFixture<RanijePrijaveDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RanijePrijaveDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RanijePrijaveDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
