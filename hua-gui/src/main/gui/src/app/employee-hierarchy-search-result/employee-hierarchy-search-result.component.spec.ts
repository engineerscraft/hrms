import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeHierarchySearchResultComponent } from './employee-hierarchy-search-result.component';

describe('EmployeeHierarchySearchResultComponent', () => {
  let component: EmployeeHierarchySearchResultComponent;
  let fixture: ComponentFixture<EmployeeHierarchySearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeHierarchySearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeHierarchySearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
