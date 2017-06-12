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
          this.employeeService.autoComplete('firstName', firstName)
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
          this.employeeService.autoComplete('middleName', middleName)
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
          this.employeeService.autoComplete('lastName', lastName)
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
          this.employeeService.autoComplete('emailId', emailId)
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

  suggestFirstName() {



  }

  suggestLastName() {

  }

  suggestEmailId() {
    
  }

  isSearchingInProgress() {

  }

  search() {

  }

  onReset() {

  }
  

}
