import { TestBed } from '@angular/core/testing';

import { ZigXmlConverterService } from './zig-xml-converter.service';

describe('ZigXmlConverterService', () => {
  let service: ZigXmlConverterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZigXmlConverterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
