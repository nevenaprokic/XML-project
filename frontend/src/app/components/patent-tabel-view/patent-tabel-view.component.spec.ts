import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentTabelViewComponent } from './patent-tabel-view.component';

describe('PatentTabelViewComponent', () => {
  let component: PatentTabelViewComponent;
  let fixture: ComponentFixture<PatentTabelViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentTabelViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentTabelViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
