import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageEmployeeComponent } from './home-page-employee.component';

describe('HomePageEmployeeComponent', () => {
  let component: HomePageEmployeeComponent;
  let fixture: ComponentFixture<HomePageEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageEmployeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePageEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
