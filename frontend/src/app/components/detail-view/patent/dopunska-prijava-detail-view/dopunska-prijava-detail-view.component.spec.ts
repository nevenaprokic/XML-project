import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DopunskaPrijavaDetailViewComponent } from './dopunska-prijava-detail-view.component';

describe('DopunskaPrijavaDetailViewComponent', () => {
  let component: DopunskaPrijavaDetailViewComponent;
  let fixture: ComponentFixture<DopunskaPrijavaDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DopunskaPrijavaDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DopunskaPrijavaDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
