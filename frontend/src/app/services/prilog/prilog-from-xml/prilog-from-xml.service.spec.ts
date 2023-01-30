import { TestBed } from '@angular/core/testing';

import { PrilogFromXmlService } from './prilog-from-xml.service';

describe('PrilogFromXmlService', () => {
  let service: PrilogFromXmlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrilogFromXmlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
