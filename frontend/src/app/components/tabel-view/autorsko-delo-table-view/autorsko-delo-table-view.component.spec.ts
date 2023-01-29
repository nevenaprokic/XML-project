import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorskoDeloTableViewComponent } from './autorsko-delo-table-view.component';

describe('AutorskoDeloTableViewComponent', () => {
  let component: AutorskoDeloTableViewComponent;
  let fixture: ComponentFixture<AutorskoDeloTableViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutorskoDeloTableViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutorskoDeloTableViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
