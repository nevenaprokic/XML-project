import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdresaDetailViewComponent } from './adresa-detail-view.component';

describe('AdresaDetailViewComponent', () => {
  let component: AdresaDetailViewComponent;
  let fixture: ComponentFixture<AdresaDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdresaDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdresaDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
