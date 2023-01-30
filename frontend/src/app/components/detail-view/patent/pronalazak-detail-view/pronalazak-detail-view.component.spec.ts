import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PronalazakDetailViewComponent } from './pronalazak-detail-view.component';

describe('PronalazakDetailViewComponent', () => {
  let component: PronalazakDetailViewComponent;
  let fixture: ComponentFixture<PronalazakDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PronalazakDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PronalazakDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
