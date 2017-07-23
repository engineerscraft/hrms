import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import 'rxjs/add/operator/finally';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DocTypeService } from '../services/doc-type.service';
import { Observable } from 'rxjs/Observable';
import { CountryService } from '../services/country.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  private employeeInfo;
  private id;
  private processingInProgress = false;
  private showEditBasicInfo = false;
  private formGroupBasicInfo: FormGroup;
  private identityDocTypes;
  private countries;

  constructor(private formBuilder: FormBuilder,
    private employeeService: EmployeeService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private docTypeService: DocTypeService,
    private countryService: CountryService, ) {
  }

  ngOnInit() {

    this.activatedRoute
      .queryParams
      .subscribe(params => {
        this.id = params['id'];
        this.processingInProgress = true;
        let employeeBasicInfoObservable = this.employeeService.readDetails(this.id)
          .finally(() => { this.processingInProgress = false; })
          .subscribe(data => {
            this.employeeInfo = data;
          },
          (err: any) => {
            if (err.status === 401 && err.json()["message"] !== "Refresh token expired") {
              this.router.navigate(['forbidden']);
            }
            if (err.status === 404) {
              this.router.navigate(['404']);
            }
          },
          () => {
            this.formGroupBasicInfo = this.formBuilder.group({
              'title': [this.employeeInfo.employeeBasicInfo.title, Validators.required],
              'empFirstName': [this.employeeInfo.employeeBasicInfo.empFirstName, Validators.required],
              'empMiddleName': [this.employeeInfo.employeeBasicInfo.empMiddleName],
              'empLastName': [this.employeeInfo.employeeBasicInfo.empLastName, Validators.required],
              'fatherName': [this.employeeInfo.employeeBasicInfo.fatherName],
              'dob': [this.employeeInfo.employeeBasicInfo.dob, Validators.required],
              'emailId': [this.employeeInfo.employeeBasicInfo.emailId],
              'contactNo': [this.employeeInfo.employeeBasicInfo.contactNo, Validators.required],
              'nationality': [this.employeeInfo.employeeBasicInfo.nationality, Validators.required],
              'doj': [this.employeeInfo.employeeBasicInfo.doj],
              'organizationId': [this.employeeInfo.employeeBasicInfo.organizationId],
              'department': this.formBuilder.group({
                'departmentId': [this.employeeInfo.employeeBasicInfo.department.departmentId]
              }),
              'unit': this.formBuilder.group({
                'unitId': [this.employeeInfo.employeeBasicInfo.unit.unitId]
              }),
              'empType': [this.employeeInfo.employeeBasicInfo.empType, Validators.required],
              'sex': [this.employeeInfo.employeeBasicInfo.sex, Validators.required],
              'maritalStatus': [this.employeeInfo.employeeBasicInfo.maritalStatus, Validators.required],
              'identityDocType': this.formBuilder.group({
                'docTypeId': [this.employeeInfo.employeeBasicInfo.identityDocType.docTypeId, Validators.required]
              }),
              'identityNumber': [this.employeeInfo.employeeBasicInfo.identityNumber, Validators.required],
              'hrFlag': [this.employeeInfo.employeeBasicInfo.hrFlag],
              'supervisorFlag': [this.employeeInfo.employeeBasicInfo.supervisorFlag]
            });
          });
      });
  }

  profileImageUpload(event) {
    var reader = new FileReader();
    reader.readAsDataURL(event.srcElement.files[0]);
    var me = this;
    reader.onload = function () {
      var fileContent = reader.result;
      me.processingInProgress = true;
      me.employeeService.uploadProfileImage(me.id, { "profileImage": fileContent })
        .finally(() => {
          me.processingInProgress = false;
        }
        )
        .subscribe(data => {
        },
        (err: any) => {
          if (err.status === 401 && err.json()["message"] !== "Refresh token expired") {
            me.router.navigate(['forbidden']);
          }
          if (err.status === 404) {
            me.router.navigate(['404']);
          }
        },
        () => {
          me.employeeInfo.employeeBasicInfo.profileImage = fileContent;
        });
    }
  }

  editBasicInfo() {
    let docTypeServiceObservable = this.docTypeService.getIdentityDocTypes();
    let countryServiceObservable = this.countryService.getCountries();
    Observable.forkJoin([docTypeServiceObservable, countryServiceObservable])
      .finally(() => { this.processingInProgress = false; })
      .subscribe(data => {
        this.identityDocTypes = data[0];
        this.countries = data[1];
      },
      (err: any) => {
        if (err.status === 401 && err.json()["message"] !== "Refresh token expired") {
          this.router.navigate(['forbidden']);
        }
        if (err.status === 404) {
          this.router.navigate(['404']);
        }
      },
      () => {
        this.showEditBasicInfo = true;
      });
    
  }

  getShowEditBasicInfo() {
    return this.showEditBasicInfo;
  }

  onCancel() {
    this.showEditBasicInfo = false;
  }

  onBasicInfoUpdate() {
    this.processingInProgress = true;
    this.employeeService.updateBasicInfo(this.id, this.formGroupBasicInfo.value)
      .finally(() => {
        this.processingInProgress = false;
        this.showEditBasicInfo = false;
      }
      )
      .subscribe(data => {
      },
      (err: any) => {
        if (err.status === 401 && err.json()["message"] !== "Refresh token expired") {
          this.router.navigate(['forbidden']);
        }
        if (err.status === 404) {
          this.router.navigate(['404']);
        }
      },
      () => {
        this.employeeInfo.employeeBasicInfo = Object.assign(this.employeeInfo.employeeBasicInfo, this.formGroupBasicInfo.value);
      });
  }

  isProcessingInProgress() {
    return this.processingInProgress;
  }
}
