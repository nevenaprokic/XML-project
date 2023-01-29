import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KontaktDetailViewComponent } from './kontakt-detail-view.component';

describe('KontaktDetailViewComponent', () => {
  let component: KontaktDetailViewComponent;
  let fixture: ComponentFixture<KontaktDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KontaktDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KontaktDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
