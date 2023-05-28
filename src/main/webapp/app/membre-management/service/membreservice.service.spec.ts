import { TestBed } from '@angular/core/testing';

import { MembreserviceService } from './membreservice.service';

describe('MembreserviceService', () => {
  let service: MembreserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MembreserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
