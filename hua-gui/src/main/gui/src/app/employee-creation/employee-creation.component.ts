import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DepartmentService } from '../services/department.service';
import { OrganizationService } from '../services/organization.service';
import { UnitService } from '../services/unit.service';
import { DocTypeService } from '../services/doc-type.service';
import { Observable } from 'rxjs/Observable';
import { CountryService } from '../services/country.service';
import { EmployeeService } from '../services/employee.service';
import { DistrictService } from '../services/district.service';
import { StateService } from '../services/state.service';
import { JobRoleService } from '../services/job-role.service';
import { CustomValidator } from '../validators/custom-validators';

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
  private jobRoles;
  private salaryComponents;
  private optionalBenefitComponents;
  private message: string = '';
  private copyAddress: boolean;
  private autoCompleteSuggestions = {
    supervisorEmailIdSuggestions: Array(), hrEmailIdSuggestions: Array()
  };
  private valueChanged = true;

  constructor(private formBuilder: FormBuilder,
    private organizationService: OrganizationService,
    private unitService: UnitService,
    private departmentService: DepartmentService,
    private docTypeService: DocTypeService,
    private countryService: CountryService,
    private employeeService: EmployeeService,
    private districtService: DistrictService,
    private stateService: StateService,
    private jobRoleService: JobRoleService) { }



  ngOnInit() {
    this.employeeInfo = this.formBuilder.group({
      'employeeBasicInfo': this.initBasicInfoControls(),
      'employeeAddress': this.formBuilder.group({
        'permanent': this.initAddressGroup('permanent'),
        'present': this.initAddressGroup('present')
      }),
      'employeeAddlDetails': this.initAdditionalInfoControls(),
      'employeeJobRoleDetails': this.formBuilder.group({
        'jobRoleId': ['', Validators.required]
      }),
      'otherDetails': this.otherDetailsControls(),
      'employeeProfile': this.formBuilder.group({
        'qualification': [''],
        'description': [''],
        'comments': ['']
      })
    });
    this.initiateLists();

    this.employeeInfo.controls.otherDetails.get("hrEmailId").valueChanges.debounceTime(400)
      .subscribe(
      emailId => {
        this.employeeService.autoComplete('hrEmailId', emailId)
          .subscribe(
          data => {
            console.log(data);
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

    this.employeeInfo.controls.otherDetails.get("supervisorEmailId").valueChanges.debounceTime(400)
      .subscribe(
      emailId => {
        this.employeeService.autoComplete('supervisorEmailId', emailId)
          .subscribe(
          data => {
            console.log(data);
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
  }



  private otherDetailsControls() {
    return this.formBuilder.group({
      'hrEmailId': [''],
      'supervisorEmailId': [''],
      'cl': [''],
      'maternityLeave': [],
      'paternityLeave': [],
      'pl': [],
      'specialLeave': [],
      'sickLeave': [],
      'probationPeriodEndDate': ['']
    });
  }


  private initAdditionalInfoControls() {
    return this.formBuilder.group({
      'dependentNo': [''],
      'siblingNo': [''],
      'emergencyContactName': [''],
      'emergencyContactNo': [''],
      'medicalReportComment': [''],
      'preMedicalCheckUpDate': [''],
      'nomineeName1': [''],
      'nomineeShare1': [''],
      'nomineeName2': [''],
      'nomineeShare2': [''],
      'nomineeName3': [''],
      'nomineeShare3': ['']
    });
  }

  private initBasicInfoControls() {
    return this.formBuilder.group({
      'title': ['', Validators.required],
      'empFirstName': ['', Validators.required],
      'empMiddleName': [''],
      'empLastName': ['', Validators.required],
      'fatherName': [''],
      'dob': ['', Validators.compose([Validators.required, CustomValidator.properDate])],
      'emailId': ['', Validators.compose([Validators.required, CustomValidator.validEmail])],
      'contactNo': ['', Validators.compose([Validators.required, CustomValidator.validPhone])],
      'nationality': ['', Validators.required],
      'doj': ['', Validators.compose([Validators.required, CustomValidator.properDate])],
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
      'identityNumber': ['', Validators.required],
      'hrFlag': [''],
      'supervisorFlag': ['']
    });
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


  OnClickOk() {
    this.message = '';
  }

  private canCreate() {
    return !(this.employeeInfo.controls.employeeBasicInfo.valid);
  }

  /**
   * Initialize all the static lists
   */
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

  /**
   * Used to compare a salary compoenet against the max value allowed
   * @param value 
   * @param maxValue 
   */
  private checkLimit(value, maxValue) {
    if (value > maxValue)
      return "red";
    else
      return "";
  }

  /**
   * Job role change handler, refresh the salary compoenents
   * @param jobRoleId 
   */
  private onJobRoleChange(jobRoleId) {
    console.log(jobRoleId);
    let salaryObservable = this.jobRoleService.getSalaryByJobRoleId(jobRoleId);
    let optionalCompObservable = this.jobRoleService.getOptionalBenefitsByJobRoleId(jobRoleId);
    this.processingInProgress = true;
    let dummySalComponents: any[];

    Observable.forkJoin([salaryObservable, optionalCompObservable])
      .subscribe(
      data => {
        this.salaryComponents = data[0];
        for (let comp of this.salaryComponents) {
          comp['selected'] = true;
        }
        this.optionalBenefitComponents = data[1];
        for (let comp of this.optionalBenefitComponents) {
          comp['selected'] = false;
        }
      },
      (err: any) => {
        this.processingInProgress = false;
      },
      () => {
        this.processingInProgress = false;
      }
      );
    console.log(JSON.stringify(this.salaryComponents))
  }

  /**
   * Organization change handler
   * @param orgId 
   */
  onOrgChange(orgId) {
    console.log(orgId);
    if (orgId === "")
      return;
    this.processingInProgress = true;
    let unitObservable = this.unitService.getUnits(orgId);
    let jobRoleObservable = this.jobRoleService.getJobRolesByOrgId(orgId);

    this.processingInProgress = true;
    Observable.forkJoin([unitObservable, jobRoleObservable])
      .subscribe(
      data => {
        this.units = data[0];
        this.jobRoles = data[1];
      },
      (err: any) => {
        this.processingInProgress = false;
      },
      () => {
        this.processingInProgress = false;
      }
      );
  }

  /**
   * Country change handler
   * @param countryId 
   * @param addressType 
   */
  onCountryChange(countryId, addressType: string) {
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

  /**
   * State change handler (works for both present and permanent address)
   * @param stateId 
   * @param addressType 
   */
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

  /**
   * Unit change handler
   * @param unitId 
   */
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
   * Check the value of the copyAddress field and act accordingly
   */
  copyFromPermanent() {

    if (this.copyAddress === false)
      return;
    const houseNo = this.employeeInfo.get('employeeAddress').get('permanent').get('houseNo');
    const streetName = this.employeeInfo.get('employeeAddress').get('permanent').get('streetName');
    const region = this.employeeInfo.get('employeeAddress').get('permanent').get('region');
    const area = this.employeeInfo.get('employeeAddress').get('permanent').get('area');
    const districtId = this.employeeInfo.get('employeeAddress').get('permanent').get('districtId');
    const stateId = this.employeeInfo.get('employeeAddress').get('permanent').get('stateId');
    const countryId = this.employeeInfo.get('employeeAddress').get('permanent').get('countryId');
    const pinno = this.employeeInfo.get('employeeAddress').get('permanent').get('pinno');

    this.districtsPresent = this.districtsPermanent;
    this.statesPresent = this.statesPermanent;
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
   * Crates the employee and shows the EMployee ID created
   */
  create() {
    var json = JSON.stringify(this.employeeInfo.controls.employeeBasicInfo.value);

    var salaryComponents: Array<any> = [];
    if (this.salaryComponents) {
      for (let comp of this.salaryComponents) {
        if (comp['selected'] === true) {
          let obj = {
            'salaryComponent': { 'compId': comp['salCompId'] },
            'salaryValue': comp['salValue']
          }
          salaryComponents.push(obj);
        }
      }
    }

    var obj = {
      'employeeBasicInfo': this.employeeInfo.controls.employeeBasicInfo.value,
      'employeeAddress': [this.employeeInfo.controls.employeeAddress.get('permanent').value,
      this.employeeInfo.controls.employeeAddress.get('present').value],
      'employeeAddlDetails': this.employeeInfo.controls.employeeAddlDetails.value,
      'employeeSalary': salaryComponents,
      'employeeHierarchy': this.employeeInfo.controls.otherDetails.value,
      'employeeProfile': this.employeeInfo.controls.employeeProfile.value
    };
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

  /**
   * 
   * @param event Date change handler for Employee Creation
   * the labelName guides which field is to be changed
   * @param labelName 
   */
  changeDate(event, labelName) {

    if (labelName === 'preMedicalCheckUpDate') {
      if (event.type === 'dateChanged') {
        this.employeeInfo.get('employeeAddlDetails').patchValue({ preMedicalCheckUpDate: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.employeeInfo.get('employeeAddlDetails').patchValue({ preMedicalCheckUpDate: '' });
      }
    } else if (labelName === 'dob') {
      if (event.type === 'dateChanged') {
        this.employeeInfo.get('employeeBasicInfo').patchValue({ dob: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.employeeInfo.get('employeeBasicInfo').patchValue({ dob: '' });
      }
    } else if (labelName === 'doj') {
      if (event.type === 'dateChanged') {
        this.employeeInfo.get('employeeBasicInfo').patchValue({ doj: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.employeeInfo.get('employeeBasicInfo').patchValue({ doj: '' });
      }
    } else if (labelName === 'probationPeriodEndDate') {
      if (event.type === 'dateChanged') {
        this.employeeInfo.get('otherDetails').patchValue({ probationPeriodEndDate: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.employeeInfo.get('otherDetails').patchValue({ probationPeriodEndDate: '' });
      }
    }
  }
}
