import { TestBed } from '@angular/core/testing';

import { ErrorFromXmlService } from './error-from-xml.service';

describe('ErrorFromXmlService', () => {
  let service: ErrorFromXmlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorFromXmlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
