import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '../department.service';
import { OrganizationService } from '../organization.service';
import { UnitService } from '../unit.service';
import { DocTypeService } from '../doc-type.service';
import { JobRoleService } from '../job-role.service';
import { DesignationService } from '../designation.service';
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

  constructor(private formBuilder: FormBuilder, 
              private organizationService: OrganizationService, 
              private unitService: UnitService, 
              private departmentService: DepartmentService, 
              private docTypeService: DocTypeService, 
              private jobRoleService: JobRoleService, 
              private designationService: DesignationService,
              private router: Router) { }

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
      jobRoleId: ['', []],
      isSupervisor: ['', []],
      isHr: ['', []],
      supervisorEmailId: ['', []],
      hrEmailId: ['', []],
      sex: ['', []],
      identityDoc: ['', []],
      identityDocNumber: ['', []]
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
        data=> {
          this.designations = data;
        }
      )
   }
}
