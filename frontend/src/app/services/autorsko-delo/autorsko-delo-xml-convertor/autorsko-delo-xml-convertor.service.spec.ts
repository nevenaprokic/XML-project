import { TestBed } from '@angular/core/testing';

import { AutorskoDeloXmlConvertorService } from './autorsko-delo-xml-convertor.service';

describe('AutorskoDeloXmlConvertorService', () => {
  let service: AutorskoDeloXmlConvertorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutorskoDeloXmlConvertorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
