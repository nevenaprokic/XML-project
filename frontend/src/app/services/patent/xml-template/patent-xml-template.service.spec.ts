import { TestBed } from '@angular/core/testing';

import { PatentXmlTemplateService } from './patent-xml-template.service';

describe('PatentXmlTemplateService', () => {
  let service: PatentXmlTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatentXmlTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
