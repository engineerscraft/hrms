import { TestBed, inject } from '@angular/core/testing';

import { OrganizationService } from './organization.service';

describe('OrganizationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OrganizationService]
    });
  });

  it('should ...', inject([OrganizationService], (service: OrganizationService) => {
    expect(service).toBeTruthy();
  }));
});
