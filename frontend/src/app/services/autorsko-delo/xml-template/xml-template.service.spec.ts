import { TestBed } from '@angular/core/testing';

import { XMLTemplateService } from './xml-template.service';

describe('XMLTemplateService', () => {
  let service: XMLTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(XMLTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
