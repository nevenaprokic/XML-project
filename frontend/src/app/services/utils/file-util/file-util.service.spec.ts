import { TestBed } from '@angular/core/testing';

import { FileUtilService } from './file-util.service';

describe('FileUtilService', () => {
  let service: FileUtilService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileUtilService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
