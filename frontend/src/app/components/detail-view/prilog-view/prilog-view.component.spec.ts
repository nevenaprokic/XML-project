import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrilogViewComponent } from './prilog-view.component';

describe('PrilogViewComponent', () => {
  let component: PrilogViewComponent;
  let fixture: ComponentFixture<PrilogViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrilogViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrilogViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
