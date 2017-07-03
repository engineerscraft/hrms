import { TestBed, inject } from '@angular/core/testing';

import { DistrictService } from './district.service';

describe('DistrictService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DistrictService]
    });
  });

  it('should ...', inject([DistrictService], (service: DistrictService) => {
    expect(service).toBeTruthy();
  }));
});
