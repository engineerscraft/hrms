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
import {EventEmitter} from '@angular/core';
import 'rxjs/add/operator/debounceTime.js';

@Component({
  selector: 'app-employee-management',
  templateUrl: './employee-management.component.html',
  styleUrls: ['./employee-management.component.css']
})
export class EmployeeManagementComponent implements OnInit {

  private processingInProgress = false;
  private colleges;
  private departments;
  private designations;
  private formGroupSearch: FormGroup;
  private firstNameSuggestions;
  private middleNameSuggestions;
  private lastNameSuggestions;
  private emailIdSuggestions;
  private employees: Array<any>;
  private searchResult: Array<any>;
  private searchResultSetSize = 0;
  private pageSize = 20;
  private lowerRange = 0;
  private upperRange = 0;
  private disableNext = false;
  private disablePrevious = false;
  private searchResultAvailable = false;

  constructor(private formBuilder: FormBuilder, private collegeService: CollegeService, private departmentService: DepartmentService, private designationService: DesignationService, private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
  this.formGroupSearch = this.formBuilder.group({
      firstName: ['', []],
      middleName: ['', []],
      lastName: ['', []],
      designation: ['', []],
      department: ['', []],
      college: ['', []],
      employeeId: ['', []],
      emailId: ['', []],
      contactNo: ['', []] 
    });

    this.formGroupSearch.get("firstName").valueChanges.debounceTime(400)
      .subscribe(
        firstName => {
          this.employeeService.autoComplete('FIRST_NAME', firstName)
            .subscribe(
              data => {
                this.firstNameSuggestions = data;
              }
            )
        }
      )

    this.formGroupSearch.get("middleName").valueChanges.debounceTime(400)
      .subscribe(
        middleName => {
          this.employeeService.autoComplete('MIDDLE_NAME', middleName)
            .subscribe(
              data => {
                this.middleNameSuggestions = data;
              }
            )
        }
      )

    this.formGroupSearch.get("lastName").valueChanges.debounceTime(400)
      .subscribe(
        lastName => {
          this.employeeService.autoComplete('LAST_NAME', lastName)
            .subscribe(
              data => {
                this.lastNameSuggestions = data;
              }
            )
        }
      )

    this.formGroupSearch.get("emailId").valueChanges.debounceTime(400)
      .subscribe(
        emailId => {
          this.employeeService.autoComplete('EMAIL_ADDRESS', emailId)
            .subscribe(
              data => {
                this.emailIdSuggestions = data;
              }
            )
        }
      )

   this.processingInProgress = true;
    let collegeObservable = this.collegeService.getColleges();
    let departmentObservable = this.departmentService.getDepartments();
    let designationObservable = this.designationService.getDesignations();


    Observable.forkJoin([collegeObservable, departmentObservable, designationObservable])
      .subscribe(
        data => {
          this.colleges = data[0];
          this.departments = data[1];
          this.designations = data[2];
        },
        (err: any) => {
          this.processingInProgress = false;
        },
        () => {
          this.processingInProgress = false;
        }
      );
  }

  search() {
    this.processingInProgress = true;
    this.employeeService.search(this.formGroupSearch)
      .subscribe(
      res => {
        this.searchResult = res;
        this.lowerRange = 1;
        if(this.searchResult.length >= this.pageSize) {
          this.employees = this.searchResult.slice(0,this.pageSize);
          this.upperRange = this.lowerRange + this.pageSize - 1;
          this.disableNext = false;
          this.disablePrevious = true;
        }
        else {
          this.employees = this.searchResult;
          this.upperRange = this.searchResult.length;
          this.disableNext = true;
          this.disablePrevious = true;
        }
        this.searchResultSetSize = this.searchResult.length;
        this.searchResultAvailable = true;
      },
      err => {
        this.processingInProgress = false;
      },
      () => {
        this.processingInProgress = false;
      });    
  }

  next() {
    if(this.disableNext)
      return;
    this.lowerRange = this.lowerRange + this.pageSize;
    this.upperRange = this.lowerRange + this.pageSize - 1;
    this.disablePrevious = false;
    if(this.upperRange >= this.searchResultSetSize) {
      this.upperRange = this.searchResultSetSize;
      this.disableNext = true;
    }
    this.employees = this.searchResult.slice(this.lowerRange -1,this.upperRange);
  }

  previous() {
    if(this.disablePrevious)
      return;
    this.lowerRange = this.lowerRange - this.pageSize;
    this.upperRange = this.lowerRange + this.pageSize - 1;
    this.disableNext = false;
    if(this.lowerRange <=1) {
      this.lowerRange = 1;
      this.disablePrevious = true;
    }
    this.employees = this.searchResult.slice(this.lowerRange -1,this.upperRange);
  }

  modifySearchCriteria() {
    this.searchResultAvailable = false;
  }

}
