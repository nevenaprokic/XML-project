import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PravnoLiceDetailViewComponent } from './pravno-lice-detail-view.component';

describe('PravnoLiceDetailViewComponent', () => {
  let component: PravnoLiceDetailViewComponent;
  let fixture: ComponentFixture<PravnoLiceDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PravnoLiceDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PravnoLiceDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
