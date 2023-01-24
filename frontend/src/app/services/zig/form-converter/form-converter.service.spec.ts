import { TestBed } from '@angular/core/testing';

import { FormConverterService } from './form-converter.service';

describe('FormConverterService', () => {
  let service: FormConverterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormConverterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
