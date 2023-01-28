import { TestBed } from '@angular/core/testing';

import { PatentToXmlService } from './patent-to-xml.service';

describe('PatentToXmlService', () => {
  let service: PatentToXmlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatentToXmlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
