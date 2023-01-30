import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorskoDeloDetailViewComponent } from './autorsko-delo-detail-view.component';

describe('AutorskoDeloDetailViewComponent', () => {
  let component: AutorskoDeloDetailViewComponent;
  let fixture: ComponentFixture<AutorskoDeloDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutorskoDeloDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutorskoDeloDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
