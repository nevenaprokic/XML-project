import { TestBed } from '@angular/core/testing';

import { PatentFromXmlService } from './patent-from-xml.service';

describe('PatentFromXmlService', () => {
  let service: PatentFromXmlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatentFromXmlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
