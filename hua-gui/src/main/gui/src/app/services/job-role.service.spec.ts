import { TestBed, inject } from '@angular/core/testing';

import { JobRoleService } from './job-role.service';

describe('JobRoleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JobRoleService]
    });
  });

  it('should ...', inject([JobRoleService], (service: JobRoleService) => {
    expect(service).toBeTruthy();
  }));
});
