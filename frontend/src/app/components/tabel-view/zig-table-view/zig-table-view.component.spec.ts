import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZigTableViewComponent } from './zig-table-view.component';

describe('ZigTableViewComponent', () => {
  let component: ZigTableViewComponent;
  let fixture: ComponentFixture<ZigTableViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZigTableViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZigTableViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
