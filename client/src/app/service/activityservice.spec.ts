import { TestBed } from '@angular/core/testing';

import { Activityservice } from './activityservice';

describe('Activityservice', () => {
  let service: Activityservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Activityservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
