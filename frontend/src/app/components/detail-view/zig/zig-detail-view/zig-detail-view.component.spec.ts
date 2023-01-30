import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZigDetailViewComponent } from './zig-detail-view.component';

describe('ZigDetailViewComponent', () => {
  let component: ZigDetailViewComponent;
  let fixture: ComponentFixture<ZigDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZigDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZigDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
