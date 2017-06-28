import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeHierarchySearchComponent } from './employee-hierarchy-search.component';

describe('EmployeeHierarchySearchComponent', () => {
  let component: EmployeeHierarchySearchComponent;
  let fixture: ComponentFixture<EmployeeHierarchySearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeHierarchySearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeHierarchySearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
