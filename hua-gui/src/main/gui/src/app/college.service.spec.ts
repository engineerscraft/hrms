import { TestBed, inject } from '@angular/core/testing';

import { CollegeService } from './college.service';

describe('CollegeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CollegeService]
    });
  });

  it('should ...', inject([CollegeService], (service: CollegeService) => {
    expect(service).toBeTruthy();
  }));
});
