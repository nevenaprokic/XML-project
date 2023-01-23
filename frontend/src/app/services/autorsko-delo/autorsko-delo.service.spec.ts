import { TestBed } from '@angular/core/testing';

import { AutorskoDeloService } from './autorsko-delo.service';

describe('AutorskoDeloService', () => {
  let service: AutorskoDeloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutorskoDeloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
