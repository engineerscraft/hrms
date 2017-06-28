import { TestBed, inject } from '@angular/core/testing';

import { DocTypeService } from './doc-type.service';

describe('DocTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DocTypeService]
    });
  });

  it('should ...', inject([DocTypeService], (service: DocTypeService) => {
    expect(service).toBeTruthy();
  }));
});
