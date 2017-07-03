import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DepartmentService } from '../department.service';
import { OrganizationService } from '../organization.service';
import { UnitService } from '../unit.service';
import { DocTypeService } from '../doc-type.service';
import { Observable } from 'rxjs/Observable';
import { CountryService } from '../country.service';
import { EmployeeService } from '../employee.service';
import { DistrictService } from '../district.service';
import { StateService } from '../state.service';

@Component({
  selector: 'app-employee-creation',
  templateUrl: './employee-creation.component.html',
  styleUrls: ['./employee-creation.component.css']
})
export class EmployeeCreationComponent implements OnInit {

  private employeeInfo: FormGroup;
  private organizations;
  private units;
  private departments;
  private identityDocTypes;
  private processingInProgress = false;
  private countries;
  private statesPermanent;
  private statesPresent;
  private districtsPermanent;
  private districtsPresent;
  private message: string = '';


  constructor(private formBuilder: FormBuilder,
    private organizationService: OrganizationService,
    private unitService: UnitService,
    private departmentService: DepartmentService,
    private docTypeService: DocTypeService,
    private countryService: CountryService,
    private employeeService: EmployeeService,
    private districtService: DistrictService,
    private stateService: StateService) { }

  ngOnInit() {
    this.employeeInfo = this.formBuilder.group({
      'employeeBasicInfo': this.formBuilder.group({
        'title': ['', Validators.required],
        'empFirstName': ['', Validators.required],
        'empMiddleName': ['', Validators.required],
        'empLastName': ['', Validators.required],
        'fatherName': [''],
        'dob': ['', Validators.required],
        'emailId': [''],
        'contactNo': ['', Validators.required],
        'nationality': ['', Validators.required],
        'doj': ['', Validators.required],
        'organization': ['', Validators.required],
        'department': this.formBuilder.group({
          'departmentId': ['', Validators.required]
        }),
        'unit': this.formBuilder.group({
          'unitId': ['', Validators.required]
        }),
        'empType': ['', Validators.required],
        'sex': ['', Validators.required],
        'maritalStatus': ['', Validators.required],
        'identityDocType': this.formBuilder.group({
          'docTypeId': ['', Validators.required]
        }),
        'identityNumber': ['', Validators.required]
      }),
      'employeeAddress': this.formBuilder.group({
        'permanent': this.initAddressGroup('permanent'),
        'present': this.initAddressGroup('present')
      })
    });
    this.initiateLists();
  }

  OnClickOk() {
    this.message = '';
  }

  private canCreate() {
    return !(this.employeeInfo.controls.employeeBasicInfo.valid);
  }

  private initiateLists() {
    let organizationObservable = this.organizationService.getOrganizations();
    let docTypeServiceObservable = this.docTypeService.getIdentityDocTypes();
    let countryServiceObservable = this.countryService.getCountries();
    this.processingInProgress = true;
    Observable.forkJoin([organizationObservable, docTypeServiceObservable, countryServiceObservable])
      .subscribe(
      data => {
        this.organizations = data[0];
        this.identityDocTypes = data[1];
        this.countries = data[2];
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
    console.log(orgId);
    if (orgId === "")
      return;
    this.processingInProgress = true;
    let unitObservable = this.unitService.getUnits(orgId);

    this.unitService.getUnits(orgId)
      .subscribe(
      data => {
        this.units = data;
        this.processingInProgress = false;
      }, (error: any) => {
        this.processingInProgress = false;
      }, () => {
        this.processingInProgress = false;
      }
      );
  }

  onCountryChange(countryId, addressType: string) {
    console.log(countryId + '~' + addressType);
    this.processingInProgress = true;

    this.stateService.getStates(countryId)
      .subscribe(
      data => {
        if (addressType === 'permanent') {
          this.statesPermanent = data;
          console.log(JSON.stringify(this.statesPermanent));
        } else if (addressType === 'present') {
          this.statesPresent = data;
        }
        this.processingInProgress = false;
      }, (error: any) => {
        this.processingInProgress = false;
      }, () => {
        this.processingInProgress = false;
      }
      );

    var districts: any;

  }


  onStateChange(stateId, addressType: string) {
    console.log(stateId + '~' + addressType);

    this.districtService.getDistricts(stateId)
      .subscribe(
      data => {
        if (addressType === 'permanent') {
          this.districtsPermanent = data;
        } else if (addressType === 'present') {
          this.districtsPresent = data;
        }
        this.processingInProgress = false;
      }, (error: any) => {
        this.processingInProgress = false;
      }, () => {
        this.processingInProgress = false;
      }
      );


  }

  onUnitChange(unitId) {
    console.log(unitId);
    if (unitId === "")
      return;
    this.departmentService.getDepartments(unitId)
      .subscribe(
      data => {
        this.departments = data;
      }, (error: any) => {
        this.processingInProgress = false;
      }, () => {
        this.processingInProgress = false;
      }
      );
  }

  /**
   * Copy permanent address to present address
   */
  copyFromPermanent() {
    console.log('attempt to copy');
    const houseNo = this.employeeInfo.get('employeeAddress').get('permanent').get('houseNo');
    const streetName = this.employeeInfo.get('employeeAddress').get('permanent').get('streetName');
    const region = this.employeeInfo.get('employeeAddress').get('permanent').get('region');
    const area = this.employeeInfo.get('employeeAddress').get('permanent').get('area');
    const districtId = this.employeeInfo.get('employeeAddress').get('permanent').get('districtId');
    const stateId = this.employeeInfo.get('employeeAddress').get('permanent').get('stateId');
    const countryId = this.employeeInfo.get('employeeAddress').get('permanent').get('countryId');
    const pinno = this.employeeInfo.get('employeeAddress').get('permanent').get('pinno');

    this.districtsPresent = this.districtsPermanent;
    this.statesPresent    = this.statesPermanent;
    //copy the values now
    this.employeeInfo.get('employeeAddress').get('present').get('houseNo').setValue(houseNo.value);
    this.employeeInfo.get('employeeAddress').get('present').get('streetName').setValue(streetName.value);
    this.employeeInfo.get('employeeAddress').get('present').get('region').setValue(region.value);
    this.employeeInfo.get('employeeAddress').get('present').get('area').setValue(area.value);
    this.employeeInfo.get('employeeAddress').get('present').get('districtId').setValue(districtId.value);
    this.employeeInfo.get('employeeAddress').get('present').get('stateId').setValue(stateId.value);
    this.employeeInfo.get('employeeAddress').get('present').get('countryId').setValue(countryId.value);
    this.employeeInfo.get('employeeAddress').get('present').get('pinno').setValue(pinno.value);
  }

  /**
   * Create form builder group for address
   */
  initAddressGroup(type: string) {
    return this.formBuilder.group({
      'houseNo': ['', Validators.required],
      'streetName': ['', Validators.required],
      'region': [''],
      'area': [''],
      'districtId': ['', Validators.required],
      'stateId': ['', Validators.required],
      'countryId': ['', Validators.required],
      'pinno': ['', Validators.required],
      'addressType': type
    });
  }

  create() {
    var json = JSON.stringify(this.employeeInfo.controls.employeeBasicInfo.value);
    var obj = { 'employeeBasicInfo': this.employeeInfo.controls.employeeBasicInfo.value ,
                'employeeAddress' : [this.employeeInfo.controls.employeeAddress.get('permanent').value,
                                    this.employeeInfo.controls.employeeAddress.get('present').value]};
    console.log(JSON.stringify(obj));
    this.processingInProgress = true;
    this.employeeService.create(obj)
      .subscribe(
      res => {
        this.processingInProgress = false;
        this.message = res.json()['message'];
      },
      err => {
        this.message = err.json()["message"];
        this.processingInProgress = false;
      });
  }

}
