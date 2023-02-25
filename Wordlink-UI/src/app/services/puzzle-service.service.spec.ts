import { TestBed } from '@angular/core/testing';

import { PuzzleServiceService } from './puzzle-service.service';

describe('PuzzleServiceService', () => {
  let service: PuzzleServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PuzzleServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
