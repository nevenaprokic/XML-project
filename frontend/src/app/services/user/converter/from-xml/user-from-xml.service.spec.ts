import { TestBed } from '@angular/core/testing';

import { UserFromXmlService } from './user-from-xml.service';

describe('UserFromXmlService', () => {
  let service: UserFromXmlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserFromXmlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
