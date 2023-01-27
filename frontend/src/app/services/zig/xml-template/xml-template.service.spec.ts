import { TestBed } from '@angular/core/testing';

import { XmlTemplateService } from './xml-template.service';

describe('XmlTemplateService', () => {
  let service: XmlTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(XmlTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
