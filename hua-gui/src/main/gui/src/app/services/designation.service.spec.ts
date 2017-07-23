import { TestBed, inject } from '@angular/core/testing';

import { DesignationService } from './designation.service';

describe('DesignationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DesignationService]
    });
  });

  it('should ...', inject([DesignationService], (service: DesignationService) => {
    expect(service).toBeTruthy();
  }));
});
