import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZnakZigaDetailViewComponent } from './znak-ziga-detail-view.component';

describe('ZnakZigaDetailViewComponent', () => {
  let component: ZnakZigaDetailViewComponent;
  let fixture: ComponentFixture<ZnakZigaDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZnakZigaDetailViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZnakZigaDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
