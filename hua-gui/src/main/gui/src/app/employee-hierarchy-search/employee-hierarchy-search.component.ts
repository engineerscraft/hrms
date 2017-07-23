import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '../services/department.service';
import { OrganizationService } from '../services/organization.service';
import { UnitService } from '../services/unit.service';
import { DocTypeService } from '../services/doc-type.service';
import { JobRoleService } from '../services/job-role.service';
import { DesignationService } from '../services/designation.service';
import { EmployeeService } from '../services/employee.service';
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
  private identityDocTypes;
  private jobRoles;
  private designations;
  private processingInProgress = false;
  private autoCompleteSuggestions = {
    firstNameSuggestions: Array(), middleNameSuggestions: Array(),
    lastNameSuggestions: Array(), emailIdSuggestions: Array(),
    supervisorEmailIdSuggestions: Array(), hrEmailIdSuggestions: Array()
  };
  private valueChanged = true;


  constructor(private formBuilder: FormBuilder,
    private organizationService: OrganizationService,
    private unitService: UnitService,
    private departmentService: DepartmentService,
    private docTypeService: DocTypeService,
    private jobRoleService: JobRoleService,
    private designationService: DesignationService,
    private employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit() {
    this.formGroupSearch = this.formBuilder.group({
      firstName: ['', []],
      middleName: ['', []],
      lastName: ['', []],
      designationId: ['', []],
      departmentId: ['', []],
      employeeId: ['', []],
      emailId: ['', []],
      contactNo: ['', []],
      empType: ['', []],
      orgId: ['', []],
      unitId: ['', []],
      jobRoleId: ['', []],
      isSupervisor: ['', []],
      isHr: ['', []],
      supervisorEmailId: ['', []],
      hrEmailId: ['', []],
      sex: ['', []],
      identityDocTypeId: ['', []],
      identityDocNumber: ['', []],
      nationality: ['', []]
    });

    let organizationObservable = this.organizationService.getOrganizations();
    let docTypeServiceObservable = this.docTypeService.getIdentityDocTypes();

    this.processingInProgress = true;
    Observable.forkJoin([organizationObservable, docTypeServiceObservable])
      .subscribe(
      data => {
        this.organizations = data[0];
        this.identityDocTypes = data[1];
      },
      (err: any) => {
        this.processingInProgress = false;
      },
      () => {
        this.processingInProgress = false;
      }
      );


    this.formGroupSearch.get("firstName").valueChanges.debounceTime(400)
      .subscribe(
      firstName => {
        this.employeeService.autoComplete('empFirstName', firstName)
          .subscribe(
          data => {
            this.autoCompleteSuggestions.firstNameSuggestions = data;
            this.valueChanged = false;
          },
          err => {

          },
          () => {
            if (this.valueChanged === true) {
              this.autoCompleteSuggestions.firstNameSuggestions = null;
            }
            if (this.valueChanged === false) {
              this.valueChanged = true;
            }
          }
          )
      }
      );

    this.formGroupSearch.get("middleName").valueChanges.debounceTime(400)
      .subscribe(
      middleName => {
        this.employeeService.autoComplete('empMiddleName', middleName)
          .subscribe(
          data => {
            this.autoCompleteSuggestions.middleNameSuggestions = data;
            this.valueChanged = false;
          },
          err => {

          },
          () => {
            if (this.valueChanged === true) {
              this.autoCompleteSuggestions.middleNameSuggestions = null;
            }
            if (this.valueChanged === false) {
              this.valueChanged = true;
            }
          }
          )
      }
      );

    this.formGroupSearch.get("lastName").valueChanges.debounceTime(400)
      .subscribe(
      lastName => {
        this.employeeService.autoComplete('empLastName', lastName)
          .subscribe(
          data => {
            this.autoCompleteSuggestions.lastNameSuggestions = data;
            this.valueChanged = false;
          },
          err => {

          },
          () => {
            if (this.valueChanged === true) {
              this.autoCompleteSuggestions.lastNameSuggestions = null;
            }
            if (this.valueChanged === false) {
              this.valueChanged = true;
            }
          }
          )
      }
      );

    this.formGroupSearch.get("emailId").valueChanges.debounceTime(400)
      .subscribe(
      emailId => {
        this.employeeService.autoComplete('empEmailId', emailId)
          .subscribe(
          data => {
            this.autoCompleteSuggestions.emailIdSuggestions = data;
            this.valueChanged = false;
          },
          err => {

          },
          () => {
            if (this.valueChanged === true) {
              this.autoCompleteSuggestions.emailIdSuggestions = null;
            }
            if (this.valueChanged === false) {
              this.valueChanged = true;
            }
          }
          )
      }
      );

    this.formGroupSearch.get("supervisorEmailId").valueChanges.debounceTime(400)
      .subscribe(
      emailId => {
        this.employeeService.autoComplete('supervisorEmailId', emailId)
          .subscribe(
          data => {
            this.autoCompleteSuggestions.supervisorEmailIdSuggestions = data;
            this.valueChanged = false;
          },
          err => {

          },
          () => {
            if (this.valueChanged === true) {
              this.autoCompleteSuggestions.supervisorEmailIdSuggestions = null;
            }
            if (this.valueChanged === false) {
              this.valueChanged = true;
            }
          }
          )
      }
      );

    this.formGroupSearch.get("hrEmailId").valueChanges.debounceTime(400)
      .subscribe(
      emailId => {
        this.employeeService.autoComplete('hrEmailId', emailId)
          .subscribe(
          data => {
            this.autoCompleteSuggestions.hrEmailIdSuggestions = data;
            this.valueChanged = false;
          },
          err => {

          },
          () => {
            if (this.valueChanged === true) {
              this.autoCompleteSuggestions.hrEmailIdSuggestions = null;
            }
            if (this.valueChanged === false) {
              this.valueChanged = true;
            }
          }
          )
      }
      );
  }


  onOrgChange(orgId) {

    let jobRoleObservable = this.jobRoleService.getJobRolesByOrgId(orgId);
    let unitObservable = this.unitService.getUnits(orgId);

    Observable.forkJoin([jobRoleObservable, unitObservable])
      .subscribe(
      data => {
        this.jobRoles = data[0];
        this.units = data[1];
      },
      (err: any) => {

      },
      () => {

      }
      );


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

  onGradeChange(jobRoleId) {
    this.designationService.getDesignationsByJobRoleId(jobRoleId)
      .subscribe(
      data => {
        this.designations = data;
      }
      )
  }

  search() {
    this.router.navigate(['employeeHierarchySearchResult'], {
      queryParams:
      {
        firstName: this.formGroupSearch.value.firstName,
        middleName: this.formGroupSearch.value.middleName,
        lastName: this.formGroupSearch.value.lastName,
        employeeId: this.formGroupSearch.value.employeeId,
        employmentType: this.formGroupSearch.value.empType,
        emailId: this.formGroupSearch.value.emailId,
        orgId: this.formGroupSearch.value.orgId,
        unitId: this.formGroupSearch.value.unitId,
        departmentId: this.formGroupSearch.value.departmentId,
        jobRoleId: this.formGroupSearch.value.jobRoleId,
        designationId: this.formGroupSearch.value.designationId,
        supervisorFlag: this.formGroupSearch.value.isSupervisor,
        hrFlag: this.formGroupSearch.value.isHr,
        supervisorEmailId: this.formGroupSearch.value.supervisorEmailId,
        hrEmailId: this.formGroupSearch.value.hrEmailId,
        sex: this.formGroupSearch.value.sex,
        identityDocTypeId: this.formGroupSearch.value.identityDocTypeId,
        identityNumber: this.formGroupSearch.value.identityDocNumber,
      }
    });
  }
}
