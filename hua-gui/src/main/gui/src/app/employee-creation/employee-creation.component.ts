import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DepartmentService } from '../department.service';
import { OrganizationService } from '../organization.service';
import { UnitService } from '../unit.service';
import { DocTypeService } from '../doc-type.service';
import { Observable } from 'rxjs/Observable';
import { CountryService } from '../country.service';

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

//address related CSS manipulators////
  private accordionPermanent: string = 'accordion';
  private accordionPresent: string = 'accordion';
  private panelPermanent: string = 'panel';
  private panelPresent: string = 'panel';
  private showPermAddress: boolean = false;
  private showCurrAddress: boolean = false;
//////////////////////////////////////



  constructor(private formBuilder: FormBuilder,
              private organizationService: OrganizationService, 
              private unitService: UnitService, 
              private departmentService: DepartmentService, 
              private docTypeService: DocTypeService,
              private countryService: CountryService) { }

  ngOnInit() {
    this.employeeInfo = this.formBuilder.group({
      'employeeBasicInfo' : this.formBuilder.group({
        'title'         : ['', Validators.required],
        'empFirstName'  : ['', Validators.required],
        'empMiddleName' : ['', Validators.required],
        'empLastName'   : ['', Validators.required],
        'fatherName'    : [''],
        'dob'           : ['', Validators.required],
        'emailId'       : [''],
        'contactNo'     : ['', Validators.required],
        'nationality'   : ['', Validators.required],
        'doj'           : ['', Validators.required],
        'organization'  : ['', Validators.required],
        'department'    : this.formBuilder.group({
          'departmentId': ['', Validators.required]
        }),
        'unit'          : this.formBuilder.group({
          'unitId'        : ['', Validators.required]
        }),
        'empType'       : ['', Validators.required],
        'sex'           : ['', Validators.required],
        'maritalStatus' : ['', Validators.required],
      'identityDocType' : this.formBuilder.group({
          'docTypeId'     : ['', Validators.required]
        }),
        'identityNumber':['', Validators.required]
      }),
      'employeeAddress' : this.formBuilder.group({
         'permanent' : this.initAddressGroup(),
         'present'   : this.initAddressGroup()
       })
    });
    this.initiateLists();
  }

  private canCreate() {
    return !(this.employeeInfo.controls.employeeBasicInfo.valid);
  }

  private initiateLists() {
    let organizationObservable    = this.organizationService.getOrganizations();
    let docTypeServiceObservable  = this.docTypeService.getIdentityDocTypes();
    let countryServiceObservable  = this.countryService.getCountries();
    this.processingInProgress     = true;
    Observable.forkJoin([organizationObservable, docTypeServiceObservable, countryServiceObservable])
      .subscribe(
      data => {
        this.organizations    = data[0];
        this.identityDocTypes = data[1];
        this.countries        = data[2];
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
    if(orgId === "")
      return;
    this.processingInProgress = true;
    let unitObservable = this.unitService.getUnits(orgId);

    this.unitService.getUnits(orgId)
      .subscribe(
        data => {
          this.units = data;
          this.processingInProgress = false;
        },(error: any) => {
          this.processingInProgress = false;
        },() => {
          this.processingInProgress = false;
        }
      ); 
   }

   onUnitChange(unitId) {
     console.log(unitId);
    if(unitId === "")
      return;
    this.departmentService.getDepartments(unitId)
      .subscribe(
        data => {
          this.departments = data;
        },(error: any) => {
          this.processingInProgress = false;
        },() => {
          this.processingInProgress = false;
        }
      ); 
   }

/**
 * Copy permanent address to present address
 */
  copyFromPermanent() {
    console.log('attempt to copy');
    const houseNo       = this.employeeInfo.get('employeeAddress').get('permanent').get('houseNo');
    const streetName    = this.employeeInfo.get('employeeAddress').get('permanent').get('streetName');
    const region        = this.employeeInfo.get('employeeAddress').get('permanent').get('region');
    const area          = this.employeeInfo.get('employeeAddress').get('permanent').get('area');
    const districtName  = this.employeeInfo.get('employeeAddress').get('permanent').get('districtName');
    const stateName     = this.employeeInfo.get('employeeAddress').get('permanent').get('stateName');
    const countryName   = this.employeeInfo.get('employeeAddress').get('permanent').get('countryName');
    const pinno         = this.employeeInfo.get('employeeAddress').get('permanent').get('pinno');
    //copy the values now
    this.employeeInfo.get('employeeAddress').get('present').get('houseNo').setValue(houseNo.value);
    this.employeeInfo.get('employeeAddress').get('present').get('streetName').setValue(streetName.value);
    this.employeeInfo.get('employeeAddress').get('present').get('region').setValue(region.value);
    this.employeeInfo.get('employeeAddress').get('present').get('area').setValue(area.value);
    this.employeeInfo.get('employeeAddress').get('present').get('districtName').setValue(districtName.value);
    this.employeeInfo.get('employeeAddress').get('present').get('stateName').setValue(stateName.value);
    this.employeeInfo.get('employeeAddress').get('present').get('countryName').setValue(countryName.value);
    this.employeeInfo.get('employeeAddress').get('present').get('pinno').setValue(pinno.value);
  }

/**
 * Create form builder group for address
 */
  initAddressGroup() {
    return this.formBuilder.group({
      'houseNo':    ['', Validators.required],
      'streetName': ['', Validators.required],
      'region':     [''],
      'area':       [''],
      'districtName':['', Validators.required],
      'stateName':  ['', Validators.required],
      'countryName':['', Validators.required],
      'pinno':      ['', Validators.required]
    });
  }

  create() {
    var json = JSON.stringify(this.employeeInfo.value);
    console.log(json);
  }


/**
 * To enable disable permanent and present address options
 * @param addType 
 */
  toggleAddress(addType: string){
    if(addType === 'permanent'){
      if(this.showPermAddress){
        this.showPermAddress = false;
        this.accordionPermanent = 'accordion';
        this.panelPermanent = 'panel';
      }else{
        this.showPermAddress = true;
        this.accordionPermanent = 'accordion active';
        this.panelPermanent = 'panelActive';
        this.panelPresent='panel';
        this.showCurrAddress = false;
        this.accordionPresent = 'accordion';
      }
    }else if(addType === 'present'){
      if(this.showCurrAddress){
        this.showCurrAddress = false;
        this.accordionPresent = 'accordion';
        this.panelPresent='panel';
      }else{
        this.showCurrAddress = true;
        this.accordionPresent = 'accordion active';
        this.panelPermanent = 'panel';
        this.panelPresent='panelActive';
        this.showPermAddress = false;
        this.accordionPermanent = 'accordion';
      }
      return null;
    }

  }
}
