import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FizickoLiceDetailViewComponent } from './fizicko-lice-detail-view.component';

describe('FizickoLiceDetailViewComponent', () => {
  let component: FizickoLiceDetailViewComponent;
  let fixture: ComponentFixture<FizickoLiceDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FizickoLiceDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FizickoLiceDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
