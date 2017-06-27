import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '../department.service';
import { OrganizationService } from '../organization.service';
import { UnitService } from '../unit.service';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/forkJoin';
import 'rxjs/add/operator/map';
import { EventEmitter } from '@angular/core';
import 'rxjs/add/operator/debounceTime.js';

@Component({
  selector: 'app-employee-hierarchy-search',
  templateUrl: './employee-hierarchy-search.component.html',
  styleUrls: ['./employee-hierarchy-search.component.css']
})
export class EmployeeHierarchySearchComponent implements OnInit {

  private formGroupSearch: FormGroup;
  private organizations;
  private units;
  private departments;

  constructor(private formBuilder: FormBuilder, private organizationService: OrganizationService, private unitService: UnitService, private departmentService: DepartmentService, private router: Router) { }

  ngOnInit() {
    this.formGroupSearch = this.formBuilder.group({
      firstName: ['', []],
      middleName: ['', []],
      lastName: ['', []],
      designationId: ['', []],
      departmentId: ['', []],
      college: ['', []],
      employeeId: ['', []],
      emailId: ['', []],
      contactNo: ['', []],
      empType: ['', []],
      organizationId: ['', []],
      unitId: ['', []],
      gradeId: ['', []],
      isSupervisor: ['', []],
      isHr: ['', []],
      supervisorEmailId: ['', []],
      hrEmailId: ['', []],
      sex: ['', []],
      identityDoc: ['', []],
      identityDocNumber: ['', []]
    });

    this.organizationService.getOrganizations()
      .subscribe(
        data => {
          this.organizations = data;
        }
      );
  }

   onOrgChange(orgId) {
    this.unitService.getUnits(orgId)
      .subscribe(
        data => {
          this.units = data;
        }
      ); 
   }

   onUnitChange(unitId) {
    this.departmentService.getDepartments(unitId)
      .subscribe(
        data => {
          this.departments = data;
        }
      ); 
   }
}
