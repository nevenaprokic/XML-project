import { TestBed } from '@angular/core/testing';

import { UserToXmlService } from './user-to-xml.service';

describe('UserToXmlService', () => {
  let service: UserToXmlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserToXmlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
