import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-hierarchy-search-result',
  templateUrl: './employee-hierarchy-search-result.component.html',
  styleUrls: ['./employee-hierarchy-search-result.component.css']
})
export class EmployeeHierarchySearchResultComponent implements OnInit {

  private pagination = { pageSize: 20, lowerRange: 0, upperRange: 0, disableNext: false, disablePrevious: false, searchResultSetSize: 0 };
  private filter = { employeeId: String, employeeName: String, emailId: String, contactNumber: String, designation: String };
  private employeeSearchCriteria = {firstName: String, middleName: String, lastName: String, employeeId: String, employmentType: String, emailId: String, orgId: String, unitId: String, departmentId: String, jobRoleId: String, designationId: String, supervisorFlag: String, hrFlag: String, supervisorEmailId: String, hrEmailId: String, sex: String, identityDocTypeId: String, identityNumber: String };
  private employees;
  private searchResult: Array<any>;
  private filteredResult: Array<any>;

  
  constructor(private activatedRoute: ActivatedRoute, private router: Router, private employeeService: EmployeeService) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
        this.employeeSearchCriteria.firstName = params['firstName'];
        this.employeeSearchCriteria.middleName = params['middleName'];
        this.employeeSearchCriteria.lastName = params['lastName'];
        this.employeeSearchCriteria.employeeId = params['employeeId'];
        this.employeeSearchCriteria.employmentType = params['employmentType'];
        this.employeeSearchCriteria.emailId = params['emailId'];
        this.employeeSearchCriteria.orgId = params['orgId'];
        this.employeeSearchCriteria.unitId = params['unitId'];
        this.employeeSearchCriteria.departmentId = params['departmentId'];
        this.employeeSearchCriteria.jobRoleId = params['jobRoleId'];
        this.employeeSearchCriteria.designationId = params['designationId'];
        this.employeeSearchCriteria.supervisorFlag = params['supervisorFlag'];
        this.employeeSearchCriteria.hrFlag = params['hrFlag'];
        this.employeeSearchCriteria.supervisorEmailId = params['supervisorEmailId'];
        this.employeeSearchCriteria.hrEmailId = params['hrEmailId'];
        this.employeeSearchCriteria.sex = params['sex'];
        this.employeeSearchCriteria.identityDocTypeId = params['identityDocTypeId'];
        this.employeeSearchCriteria.identityNumber = params['identityNumber'];
      });
    this.employeeService.search(this.employeeSearchCriteria)
    .subscribe(
      data => {
        this.searchResult = data;
        this.filteredResult = data;
        this.goToFirstPage(this.filteredResult);
      },
      (err: any) => {
        if(err.status===401) {
          this.router.navigate(['forbidden']);
        }
      },
      () => {
        
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
      if ((!!this.filter.employeeId && (new String(emp.empId)).toUpperCase().indexOf(this.filter.employeeId.toString().toUpperCase()) > -1)
        || (!!this.filter.employeeName && (new String(emp.name)).toUpperCase().indexOf(this.filter.employeeName.toString().toUpperCase()) > -1)
        || (!!this.filter.emailId && (new String(emp.emailId)).toUpperCase().indexOf(this.filter.emailId.toString().toUpperCase()) > -1)
        || (!!this.filter.contactNumber && (new String(emp.contactNo)).toUpperCase().indexOf(this.filter.contactNumber.toString().toUpperCase()) > -1)
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
    
  }
}
