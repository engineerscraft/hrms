import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeCreationNewComponent } from './employee-creation-new.component';

describe('EmployeeCreationNewComponent', () => {
  let component: EmployeeCreationNewComponent;
  let fixture: ComponentFixture<EmployeeCreationNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeCreationNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeCreationNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
