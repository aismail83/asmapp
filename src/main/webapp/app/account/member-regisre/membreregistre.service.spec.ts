import { TestBed } from '@angular/core/testing';

import { MembreregistreService } from './membreregistre.service';

describe('MembreregistreService', () => {
  let service: MembreregistreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MembreregistreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
