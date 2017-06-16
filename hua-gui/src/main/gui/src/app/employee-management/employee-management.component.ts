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
  selector: 'app-employee-management',
  templateUrl: './employee-management.component.html',
  styleUrls: ['./employee-management.component.css'],
  host: {
    '(document:click)': 'onClick($event)',
  }
})
export class EmployeeManagementComponent implements OnInit {

  private processingInProgress = false;
  private colleges;
  private departments;
  private designations;
  private formGroupSearch: FormGroup;
  private formGroupModify: FormGroup;
  private firstNameSuggestions;
  private middleNameSuggestions;
  private lastNameSuggestions;
  private emailIdSuggestions;
  private employees: Array<any>;
  private searchResult: Array<any>;
  private filteredResult: Array<any>;
  private searchResultAvailable = false;
  private pagination = { pageSize: 20, lowerRange: 0, upperRange: 0, disableNext: false, disablePrevious: false, searchResultSetSize: 0 };
  private filter = { employeeId: String, employeeName: String, emailId: String, contactNumber: String, designation: String };
  private mouseLocation: { left: number, top: number } = { 'left': 0, 'top': 0 };
  private showContextMenu = false;
  private displayViewEmployee = false;
  private selectedEmployeeId;
  private modifyAttribute = false;
  private modifyButtonName = 'Modify';

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

    this.formGroupModify = this.formBuilder.group({
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

  filterEmployee($event, fieldName) {
    if (fieldName === "employeeId")
      this.filter.employeeId = $event.target.value;
    if (fieldName === "employeeName")
      this.filter.employeeName = $event.target.value;
    if (fieldName === "emailId")
      this.filter.emailId = $event.target.value;
    if (fieldName === "contactNumber")
      this.filter.contactNumber = $event.target.value;
    if (fieldName === "designation")
      this.filter.designation = $event.target.value;
    let filteredEmp: Array<any> = new Array();



    for (let emp of this.searchResult) {
      if ((!!this.filter.employeeId && (new String(emp.id)).toUpperCase().indexOf(this.filter.employeeId.toString().toUpperCase()) > -1)
        || (!!this.filter.employeeName && (new String(emp.firstName + " " + emp.middleName + " " + emp.lastName)).toUpperCase().indexOf(this.filter.employeeName.toString().toUpperCase()) > -1)
        || (!!this.filter.emailId && (new String(emp.emailAddress)).toUpperCase().indexOf(this.filter.emailId.toString().toUpperCase()) > -1)
        || (!!this.filter.contactNumber && (new String(emp.contactNumber)).toUpperCase().indexOf(this.filter.contactNumber.toString().toUpperCase()) > -1)
        || (!!this.filter.designation && (new String(emp.designation)).toUpperCase().indexOf(this.filter.designation.toString().toUpperCase()) > -1)
      ) {
        filteredEmp.push(emp);
      }
      else if (!$event.target.value) {
        filteredEmp.push(emp);
      }
    }
    this.filteredResult = filteredEmp;
    this.goToFirstPage(this.filteredResult)
  }

  search() {
    this.processingInProgress = true;
    this.employeeService.search(this.formGroupSearch)
      .subscribe(
      res => {
        this.searchResult = res;
        this.filteredResult = res;
        this.searchResultAvailable = true;
        this.goToFirstPage(this.filteredResult);
      },
      err => {
        this.processingInProgress = false;
      },
      () => {
        this.processingInProgress = false;
      });
  }


  goToFirstPage(filteredResult) {
    this.pagination.lowerRange = filteredResult.length===0?0:1;
    if (filteredResult.length >= this.pagination.pageSize) {
      this.employees = this.filteredResult.slice(0, this.pagination.pageSize);
      this.pagination.upperRange = this.pagination.lowerRange + this.pagination.pageSize - 1;
      this.pagination.disableNext = false;
      this.pagination.disablePrevious = true;
    }
    else {
      this.employees = this.filteredResult;
      this.pagination.upperRange = this.filteredResult.length;
      this.pagination.disableNext = true;
      this.pagination.disablePrevious = true;
    }
    this.pagination.searchResultSetSize = this.filteredResult.length;
  }

  next() {
    if (this.pagination.disableNext)
      return;
    this.pagination.lowerRange = this.pagination.lowerRange + this.pagination.pageSize;
    this.pagination.upperRange = this.pagination.lowerRange + this.pagination.pageSize - 1;
    this.pagination.disablePrevious = false;
    if (this.pagination.upperRange >= this.pagination.searchResultSetSize) {
      this.pagination.upperRange = this.pagination.searchResultSetSize;
      this.pagination.disableNext = true;
    }
    this.employees = this.filteredResult.slice(this.pagination.lowerRange - 1, this.pagination.upperRange);
  }

  previous() {
    if (this.pagination.disablePrevious)
      return;
    this.pagination.lowerRange = this.pagination.lowerRange - this.pagination.pageSize;
    this.pagination.upperRange = this.pagination.lowerRange + this.pagination.pageSize - 1;
    this.pagination.disableNext = false;
    if (this.pagination.lowerRange <= 1) {
      this.pagination.lowerRange = 1;
      this.pagination.disablePrevious = true;
    }
    this.employees = this.filteredResult.slice(this.pagination.lowerRange - 1, this.pagination.upperRange);
  }

  back() {
    if(!this.displayViewEmployee) {
      this.searchResultAvailable = false;
    }
    else {
      this.displayViewEmployee = false;
      this.modifyAttribute = true;
      this.modifyButtonName = 'Save';
    }
    
  }

  showCreateEmployeePage() {
    window.scrollTo(0,0);
    this.router.navigate(['employeeCreation']);
  }

  onViewClick() {
    this.displayViewEmployee = true;
    this.processingInProgress = true;
    this.employeeService.read(this.selectedEmployeeId)
      .subscribe(
      res => {
        this.formGroupModify.get("firstName").setValue(res.firstName);
        this.formGroupModify.get("middleName").setValue(res.middleName);
        this.formGroupModify.get("lastName").setValue(res.lastName);
        this.formGroupModify.get("designation").setValue(res.designationId);
        this.formGroupModify.get("department").setValue(res.departmentId);
        this.formGroupModify.get("college").setValue(res.collegeId);
        this.formGroupModify.get("employeeId").setValue(res.id);
        this.formGroupModify.get("emailId").setValue(res.emailAddress);
        this.formGroupModify.get("contactNo").setValue(res.contactNo);
      },
      err => {
        this.processingInProgress = false;
      },
      () => {
        this.processingInProgress = false;
      });
  }

  getContextMenuCss() {
    return {
      'position': 'fixed',
      'display': this.showContextMenu ? 'block' : 'none',
      'left': this.mouseLocation.left + 'px',
      'top': this.mouseLocation.top + 'px'
    }
  }

  onRightClick(event: MouseEvent, employeeId: string) {
    this.showContextMenu = true;
    this.mouseLocation.left = event.clientX;
    this.mouseLocation.top = event.clientY;
    event.stopPropagation();
    this.selectedEmployeeId = employeeId;
    return false;
  }

  onClick(event: Event) {
    this.showContextMenu = false;
  }

  modify() {
    if(this.modifyAttribute === false) {
      this.modifyAttribute = true;
      this.modifyButtonName = 'Save';
    }
  }
}
