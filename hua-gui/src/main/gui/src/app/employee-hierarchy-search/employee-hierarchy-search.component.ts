import { Component, OnInit } from '@angular/core';
import { CollegeService } from '../college.service';
import { DepartmentService } from '../department.service';
import { DesignationService } from '../designation.service';
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

  constructor(private formBuilder: FormBuilder, private collegeService: CollegeService, private departmentService: DepartmentService, private designationService: DesignationService, private employeeService: EmployeeService, private router: Router) { }

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
  }

}
