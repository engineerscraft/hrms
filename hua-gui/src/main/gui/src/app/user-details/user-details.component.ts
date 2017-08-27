import { Component, OnInit, HostListener, ChangeDetectorRef } from '@angular/core';
import { DatePipe } from '@angular/common';
import { EmployeeService } from '../services/employee.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import 'rxjs/add/operator/finally';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { DocTypeService } from '../services/doc-type.service';
import { Observable } from 'rxjs/Observable';
import { CountryService } from '../services/country.service';
import { saveAs } from 'file-saver';
import { StateService } from '../services/state.service';
import { DistrictService } from '../services/district.service';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  private id = localStorage.getItem('userName');
  private employeeInfo;
  private processingInProgress = false;
  private showEditBasicInfo = false;
  private showEditAdditionalInfo = false;
  private showEditAddressDetails = false;
  private showEditOthersDetails = false;
  private showEditLeaveDetails = false;
  private showEditPayrollDetails = false;
  private formGroupBasicInfo: FormGroup;
  private formGroupAdditionalInfo: FormGroup;
  private formGroupAddressDetails: FormGroup;
  private formArrayAddressDetails: FormArray;
  private formGroupDocument: FormGroup;
  private formGroupOthersDetails: FormGroup;
  private formGroupLeaveDetails: FormGroup;
  private formGroupPayrollDetails: FormGroup;
  private formArrayOptionalBenefits;
  private identityDocTypes;
  private docTypes;
  private employeePaySlipInfo;
  private countries;
  private statesPermanent;
  private statesPresent;
  private districtsPermanent;
  private districtsPresent;
  private selectedDocId = 0;
  private selectedDocument = 'about:blank';
  private selectedDocDetails;
  private modalDisplay = false;
  private showDocumentEdit = false;
  private showDocumentAdd = false;
  private documentEditFunctionInvoked = false;
  private googleDocument;
  private showUpdateMessage = false;
  private showErrorMessage = false;
  private errorMessage;
  private currDateTime: Date = new Date(Date.now());
  private salaryTotal = 0;

  constructor(private formBuilder: FormBuilder,
    private employeeService: EmployeeService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private docTypeService: DocTypeService,
    private countryService: CountryService,
    private stateService: StateService,
    private districtService: DistrictService,
    private cdRef: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.employeeInfo = this.activatedRoute.snapshot.data['employeeInfo'];
    this.currDateTime.setMonth(this.currDateTime.getMonth() - 1);
    if (this.employeeInfo === undefined) {
      this.activatedRoute
        .queryParams
        .subscribe(params => {
          this.processingInProgress = true;
          let employeeBasicInfoObservable = this.employeeService.readDetails(this.id);
          let employeePaySlipObservable = this.employeeService.getPaySlip(this.id, '2017', 'January');
          Observable.forkJoin([employeeBasicInfoObservable, employeePaySlipObservable])
            .finally(() => { this.processingInProgress = false; })
            .subscribe(data => {
              this.employeeInfo = data[0];
              this.employeePaySlipInfo = data[1];
            },
            (err: any) => {
              /* if (err.status === 401 && err.json()['message'] !== 'Refresh token expired') {
                this.router.navigate(['forbidden']);
              } 
              if (err.status === 404) {
                this.router.navigate(['404']);
              } */
              this.errorMessage = err.status + ' - ' + err.json().message;
              this.showErrorMessage = true;
            },
            () => {
              this.formGroupInitializer();
            });
        });
    }
    else {
      this.formGroupInitializer();
    }
  }

  ngAfterViewChecked() {
    this.cdRef.detectChanges();
  }

  formGroupInitializer() {
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

    this.formGroupDocument = this.formBuilder.group({
      'docId': [""],
      'docTypeId': [""],
      'remarks': [""],
      'document': [""],
      'documentName': [""]
    });

    this.formGroupAdditionalInfo = this.formBuilder.group({
      'dependentNo': [this.employeeInfo.employeeAddlDetails.dependentNo],
      'siblingNo': [this.employeeInfo.employeeAddlDetails.siblingNo],
      'emergencyContactName': [this.employeeInfo.employeeAddlDetails.emergencyContactName],
      'emergencyContactNo': [this.employeeInfo.employeeAddlDetails.emergencyContactNo],
      'medicalReportComment': [this.employeeInfo.employeeAddlDetails.medicalReportComment],
      'preMedicalCheckUpDate': [this.employeeInfo.employeeAddlDetails.preMedicalCheckUpDate],
      'nomineeName1': [this.employeeInfo.employeeAddlDetails.nomineeName1],
      'nomineeShare1': [this.employeeInfo.employeeAddlDetails.nomineeShare1],
      'nomineeName2': [this.employeeInfo.employeeAddlDetails.nomineeName2],
      'nomineeShare2': [this.employeeInfo.employeeAddlDetails.nomineeShare2],
      'nomineeName3': [this.employeeInfo.employeeAddlDetails.nomineeName3],
      'nomineeShare3': [this.employeeInfo.employeeAddlDetails.nomineeShare3]
    });

    this.formGroupAddressDetails = this.formBuilder.group({
      'permanent': this.initAddressGroup('P', 0),
      'present': this.initAddressGroup('T', 1)
    });

    this.formGroupOthersDetails = this.formBuilder.group({
      'hrId': [this.employeeInfo.employeeHierarchy.hrId],
      'hrEmailId': [this.employeeInfo.employeeHierarchy.hrEmailId],
      'supervisorId': [this.employeeInfo.employeeHierarchy.supervisorId],
      'supervisorEmailId': [this.employeeInfo.employeeHierarchy.supervisorEmailId],
      'cl': [this.employeeInfo.employeeHierarchy.cl],
      'maternityLeave': [this.employeeInfo.employeeHierarchy.maternityLeave],
      'paternityLeave': [this.employeeInfo.employeeHierarchy.paternityLeave],
      'pl': [this.employeeInfo.employeeHierarchy.pl],
      'specialLeave': [this.employeeInfo.employeeHierarchy.specialLeave],
      'sickLeave': [this.employeeInfo.employeeHierarchy.sickLeave],
      'probationPeriodEndDate': [this.employeeInfo.employeeHierarchy.probationPeriodEndDate],
      'noticePeriodEndDate': [this.employeeInfo.employeeHierarchy.noticePeriodEndDate],
      'status': [this.employeeInfo.employeeHierarchy.status]
    });

    this.formGroupLeaveDetails = this.formBuilder.group({
      'eligibleCl': [this.employeeInfo.leave.eligibleCl],
      'eligiblePl': [this.employeeInfo.leave.eligiblePl],
      'eligiblePaternityMaternityLeave': [this.employeeInfo.leave.eligiblePaternityMaternityLeave],
      'eligibleSickLeave': [this.employeeInfo.leave.eligibleSickLeave],
      'eligibleSpecialLeave': [this.employeeInfo.leave.eligibleSpecialLeave],
      'availedCl': [this.employeeInfo.leave.availedCl],
      'availedPl': [this.employeeInfo.leave.availedPl],
      'availedPaternityMaternityLeave': [this.employeeInfo.leave.availedPaternityMaternityLeave],
      'availedSickLeave': [this.employeeInfo.leave.availedSickLeave],
      'availedSpecialLeave': [this.employeeInfo.leave.availedSpecialLeave]
    });

    this.formGroupPayrollDetails = this.formBuilder.group({
      formArrayOptionalBenefits: this.formBuilder.array([])
    });

    this.initOptionalComponents();

  }

  /**
   * Create form builder group for address
   */
  initAddressGroup(type: string, count: number) {
    return this.formBuilder.group({
      'houseNo': [this.employeeInfo.employeeAddress[count].houseNo],
      'streetName': [this.employeeInfo.employeeAddress[count].streetName],
      'area': [this.employeeInfo.employeeAddress[count].area],
      'pinno': [this.employeeInfo.employeeAddress[count].pinno],
      'region': [this.employeeInfo.employeeAddress[count].region],
      'districtId': [this.employeeInfo.employeeAddress[count].districtId],
      'districtName': [this.employeeInfo.employeeAddress[count].districtName],
      'stateId': [this.employeeInfo.employeeAddress[count].stateId],
      'stateName': [this.employeeInfo.employeeAddress[count].stateName],
      'countryId': [this.employeeInfo.employeeAddress[count].countryId],
      'countryName': [this.employeeInfo.employeeAddress[count].countryName],
      'addressType': type
    });
  }

  initOptionalComponents() {
    this.formArrayOptionalBenefits = this.formGroupPayrollDetails.get('formArrayOptionalBenefits') as FormArray;
    this.employeeInfo.employeeOptionalBenefit.forEach(optComp => {
       this.formArrayOptionalBenefits.push(
         this.formBuilder.group({
           'description': [optComp.optSalaryComponent.description],
           'benefitValue': [optComp.benefitValue],
           'frequency': [optComp.frequency],
           'startDate': [optComp.startDate],
           'stopDate': [optComp.stopDate],
           'nextDueDate': [optComp.nextDueDate],
           'optCompName': [optComp.optSalaryComponent.optCompName],
           'creditDebitFlag': [optComp.optSalaryComponent.creditDebitFlag],
           'totalAmount': [optComp.totalAmount],
           'isEditComponent': false
         })
       );
    });
  }

  profileImageUpload(event) {
    var reader = new FileReader();
    reader.readAsDataURL(event.srcElement.files[0]);
    var me = this;
    reader.onload = function () {
      var fileContent = reader.result;
      me.processingInProgress = true;
      me.employeeService.uploadProfileImage(me.id, { 'profileImage': fileContent })
        .finally(() => {
          me.processingInProgress = false;
        }
        )
        .subscribe(data => {
        },
        (err: any) => {
          me.errorMessage = err.status + ' - ' + err.json().message;
          me.showErrorMessage = true;
        },
        () => {
          me.employeeInfo.employeeBasicInfo.profileImage = fileContent;
          me.showUpdateMessage = true;
        });
    }
  }

  editBasicInfo() {
    if (this.identityDocTypes === undefined) {
      let docTypeServiceObservable = this.docTypeService.getIdentityDocTypes();
      let countryServiceObservable = this.countryService.getCountries();
      Observable.forkJoin([docTypeServiceObservable, countryServiceObservable])
        .finally(() => { this.processingInProgress = false; })
        .subscribe(data => {
          this.identityDocTypes = data[0];
          this.countries = data[1];
        },
        (err: any) => {
          this.errorMessage = err.status + ' - ' + err.json().message;
          this.showErrorMessage = true;
        },
        () => {
          this.showEditBasicInfo = true;
          this.modalDisplay = true;
        });
    } else {
      this.showEditBasicInfo = true;
      this.modalDisplay = true;
    }
  }

  getShowEditBasicInfo() {
    return this.showEditBasicInfo;
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
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      },
      () => {
        this.employeeInfo.employeeBasicInfo = Object.assign(this.employeeInfo.employeeBasicInfo, this.formGroupBasicInfo.value);
        this.showUpdateMessage = true;
      });
  }

  editAdditionalInfo() {
      this.showEditAdditionalInfo = true;
      this.modalDisplay = true;
  }

  getShowEditAdditionalInfo() {
    return this.showEditAdditionalInfo;
  }

  onAdditionalInfoUpdate() {
    this.processingInProgress = true;
    this.employeeService.updateAdditionalInfo(this.id, this.formGroupAdditionalInfo.value)
      .finally(() => {
        this.processingInProgress = false;
        this.showEditAdditionalInfo = false;
        this.modalDisplay = false;
        this.employeeInfo.employeeAddlDetails = Object.assign(this.employeeInfo.employeeAddlDetails, this.formGroupAdditionalInfo.value);
        this.showUpdateMessage = true;
      }
      )
      .subscribe(data => {
      },
      (err: any) => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      },
      () => {
        this.employeeInfo.employeeAddlDetails = Object.assign(this.employeeInfo.employeeAddlDetails, this.formGroupAdditionalInfo.value);
      });
  }

  editAddressDetails() {
    if (this.countries === undefined) {
      let countryServiceObservable = this.countryService.getCountries();
      let statePermanentObservable = this.stateService.getStates(this.employeeInfo.employeeAddress[0].countryId);
      let statePresentObservable = this.stateService.getStates(this.employeeInfo.employeeAddress[1].countryId);
      let districtPermanentObservable = this.districtService.getDistricts(this.employeeInfo.employeeAddress[0].stateId);
      let districtPresentObservable = this.districtService.getDistricts(this.employeeInfo.employeeAddress[1].stateId);
      Observable.forkJoin([countryServiceObservable, statePermanentObservable, statePresentObservable, districtPermanentObservable, districtPresentObservable])
        .finally(() => { this.processingInProgress = false; })
        .subscribe(data => {
          this.countries = data[0];
          this.statesPermanent = data[1];
          this.statesPresent = data[2];
          this.districtsPermanent = data[3];
          this.districtsPresent = data[4];
        },
        (err: any) => {
          this.errorMessage = err.status + ' - ' + err.json().message;
          this.showErrorMessage = true;
        },
        () => {
          this.showEditAddressDetails = true;
          this.modalDisplay = true;
        });
    } else {
      this.showEditAddressDetails = true;
      this.modalDisplay = true;
    }
  }

  getShowEditAddressDetails() {
    return this.showEditAddressDetails;
  }

  onAddressDetailsUpdate() {
    this.processingInProgress = true;
    this.formArrayAddressDetails = this.formBuilder.array([
      this.formGroupAddressDetails.controls.permanent.value,
      this.formGroupAddressDetails.controls.present.value
    ]);
    this.employeeService.updateAddressDetails(this.id, this.formArrayAddressDetails.value)
      .finally(() => {
        this.processingInProgress = false;
        this.showEditAddressDetails = false;
        this.modalDisplay = false;
        this.employeeInfo.employeeAddress = Object.assign(this.employeeInfo.employeeAddress, this.formArrayAddressDetails.value);
        this.showUpdateMessage = true;
      }
      )
      .subscribe(data => {
      },
      (err: any) => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      },
      () => {
        this.employeeInfo.employeeAddress = Object.assign(this.employeeInfo.employeeAddress, this.formArrayAddressDetails.value);
      });
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
          this.districtsPermanent = [];
        } else if (addressType === 'present') {
          this.statesPresent = data;
          this.districtsPresent = [];
        }
        this.processingInProgress = false;
      }, (err: any) => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      }, () => {
        this.processingInProgress = false;
      }
      );
  }

  /**
   * State change handler (works for both present and permanent address)
   * @param stateId 
   * @param addressType 
   */
  onStateChange(stateId, addressType: string) {
    this.processingInProgress = true;
    this.districtService.getDistricts(stateId)
      .subscribe(
      data => {
        if (addressType === 'permanent') {
          this.districtsPermanent = data;
        } else if (addressType === 'present') {
          this.districtsPresent = data;
        }
        this.processingInProgress = false;
      }, (err: any) => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      }, () => {
        this.processingInProgress = false;
      }
      );
  }

  editOthersDetails() {
      this.showEditOthersDetails = true;
      this.modalDisplay = true;
  }

  getShowEditOthersDetails() {
    return this.showEditOthersDetails;
  }

  onOthersDetailsUpdate() {
    this.processingInProgress = true;
    this.employeeService.updateOthersDetails(this.id, this.formGroupOthersDetails.value)
      .finally(() => {
        this.processingInProgress = false;
        this.showEditOthersDetails = false;
        this.modalDisplay = false;
        this.employeeInfo.employeeHierarchy = Object.assign(this.employeeInfo.employeeHierarchy, this.formGroupOthersDetails.value);
        this.showUpdateMessage = true;
      }
      )
      .subscribe(data => {
      },
      (err: any) => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      },
      () => {
        this.employeeInfo.employeeHierarchy = Object.assign(this.employeeInfo.employeeHierarchy, this.formGroupOthersDetails.value);
      });
  }

  editLeaveDetails() {
    this.showEditLeaveDetails = true;
    this.modalDisplay = true;
  }

  getShowEditLeaveDetails() {
    return this.showEditLeaveDetails;
  }

  onLeaveDetailsUpdate() {
    this.processingInProgress = true;
    this.employeeService.updateLeaveDetails(this.id, this.formGroupLeaveDetails.value)
      .finally(() => {
        this.processingInProgress = false;
        this.closeAllDialog(null);
        this.showUpdateMessage = true;
      }
      )
      .subscribe(data => {
      },
      (err: any) => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      },
      () => {
        this.showUpdateMessage = true;
        this.employeeService.readDetails(this.id)
          .finally(() => { this.processingInProgress = false; })
          .subscribe(data => {
            this.employeeInfo = data;
          },
          (err: any) => {
            this.errorMessage = err.status + ' - ' + err.json().message;
            this.showErrorMessage = true;
          },
          () => {
            this.formGroupInitializer();
          });
      });
  }

  editPayrollDetails() {
    this.showEditPayrollDetails = true;
    this.modalDisplay = true;
  }

  getShowEditPayrollDetails() {
    return this.showEditPayrollDetails;
  }

  onPayrollDetailsUpdate() {

  }

  getSelectedDocId() {
    return this.selectedDocId;
  }

  setSelectedDocId(docId) {
    this.selectedDocId = docId;
    this.employeeService.getDocument(docId, this.id)
      .subscribe(data => {
        if ((navigator.appVersion.indexOf("MSIE") !== -1) || (!!window["MSInputMethodContext"] && !!document["documentMode"])) {
          var abc=data.document.split(',')[1].replace(/\s/g, '');
          var byteString = atob(abc);
          var ab = new ArrayBuffer(byteString.length);
          var ia = new Uint8Array(ab);
          
          for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
          }
                    
          var blob = new Blob([ia], { type: 'application/pdf' });

          saveAs(blob, "hrmsdocument.pdf");
          this.selectedDocument = "hrmsdocument.pdf";
        } else {
          this.selectedDocDetails = data;
          this.selectedDocument = data.document;
        }
      },
      (err: any) => {

      });
  }

  closeAllDialog(event) {
    if (event === null || event.undefined || event.currentTarget === event.target) {
      this.showEditBasicInfo = false;
      this.showEditAdditionalInfo = false;
      this.showEditAddressDetails = false;
      this.showEditOthersDetails = false;
      this.showEditLeaveDetails = false;
      this.showEditPayrollDetails = false;
      this.modalDisplay = false;
      this.showDocumentEdit = false;
      this.showDocumentAdd = false;
      this.documentEditFunctionInvoked = false;
      this.showUpdateMessage = false;
      this.showErrorMessage = false;
    }
  }

  @HostListener('document:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.keyCode === 27) {
      this.closeAllDialog(null);
    }
  }

  editDocument() {
    if (this.docTypes === undefined && this.selectedDocDetails !== undefined) {
      let docTypeServiceObservable = this.docTypeService.getDocTypes()
        .finally(() => { this.processingInProgress = false; })
        .subscribe(data => {
          this.docTypes = data;
        },
        (err: any) => {
          this.errorMessage = err.status + ' - ' + err.json().message;
          this.showErrorMessage = true;
        },
        () => {
          this.showDocumentEdit = true;
          this.modalDisplay = true;
          this.initializeDocumentEditForm();
        });
    } else if (this.selectedDocDetails !== undefined) {
      this.showDocumentEdit = true;
      this.modalDisplay = true;
      this.initializeDocumentEditForm();
    } else {
      this.documentEditFunctionInvoked = true;
    }

  }

  initializeDocumentEditForm() {
    this.formGroupDocument.setValue({
      'docId': this.selectedDocDetails.docId,
      'docTypeId': this.selectedDocDetails.docTypeId,
      'remarks': this.selectedDocDetails.remarks,
      'document': this.selectedDocDetails.document,
      'documentName': ""
    });
  }

  initializeDocumentAddForm() {
    this.formGroupDocument.setValue({
      'docId': '',
      'docTypeId': '',
      'remarks': '',
      'document': '',
      'documentName': ''
    });
  }

  addDocument() {
    if (this.docTypes === undefined) {
      let docTypeServiceObservable = this.docTypeService.getDocTypes()
        .finally(() => {
          this.processingInProgress = false;
        })
        .subscribe(data => {
          this.docTypes = data;
        },
        (err: any) => {
          this.errorMessage = err.status + ' - ' + err.json().message;
          this.showErrorMessage = true;
        },
        () => {
          this.initializeDocumentAddForm();
          this.showDocumentAdd = true;
          this.modalDisplay = true;
        });
    } else {
      this.initializeDocumentAddForm();
      this.showDocumentAdd = true;
      this.modalDisplay = true;
    }
  }

  getShowDocumentEdit() {
    return this.showDocumentEdit;
  }

  getShowDocumentAdd() {
    return this.showDocumentAdd;
  }

  onDocumentUpdate() {
    this.processingInProgress = true;
    this.employeeService.documentSave(this.formGroupDocument.value, this.id)
      .finally(() => {
        this.processingInProgress = false;
        this.closeAllDialog(null);
      })
      .subscribe(
      res => {
        this.selectedDocument = this.formGroupDocument.value.document;
        console.log(this.formGroupDocument.value.document);
      },
      err => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      },
      () => {
        this.showUpdateMessage = true;
      });
  }

  onDocumentAdd() {
    this.processingInProgress = true;
    console.log(this.formGroupDocument.value);
    var me = this;
    this.employeeService.documentAdd(this.formGroupDocument.value, this.id)
      .finally(() => {
        this.processingInProgress = false;
        this.closeAllDialog(null);
        this.showUpdateMessage = true;
      })
      .subscribe(
      res => {},
      err => {
        this.errorMessage = err.status + ' - ' + err.json().message;
        this.showErrorMessage = true;
      },
      () => {
        this.showUpdateMessage = true;
        this.employeeService.readDetails(this.id)
          .finally(() => { this.processingInProgress = false; })
          .subscribe(data => {
            this.employeeInfo = data;
          },
          (err: any) => {
            this.errorMessage = err.status + ' - ' + err.json().message;
            this.showErrorMessage = true;
          },
          () => {
            this.formGroupInitializer();
          });
      });
  }

  documentUpload(event) {
    this.processingInProgress = true;
    console.log(event);
    this.formGroupDocument.controls['documentName'].setValue(event.srcElement.files[0].name);
    var reader = new FileReader();
    reader.readAsDataURL(event.srcElement.files[0]);
    var me = this;
    reader.onload = function () {
      me.formGroupDocument.controls['document'].setValue(reader.result);
      console.log(reader.result);
      me.processingInProgress = false;
    }
  }

  getShowSelectDocumentAlert() {
    if (this.selectedDocDetails === undefined && this.documentEditFunctionInvoked) {
      return true;
    } else {
      return false;
    }
  }

  onCancel() {
    this.showEditBasicInfo = false;
  }

  isProcessingInProgress() {
    return this.processingInProgress;
  }

  getShowUpdateMessage() {
    return this.showUpdateMessage;
  }

  getShowErrorMessage() {
    return this.showErrorMessage;
  }

  onClickUpdateMessageOk() {
    this.showUpdateMessage = false;
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
        this.formGroupAdditionalInfo.patchValue({ preMedicalCheckUpDate: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.formGroupAdditionalInfo.patchValue({ preMedicalCheckUpDate: '' });
      }
    } else if (labelName === 'dob') {
      if (event.type === 'dateChanged') {
        this.formGroupBasicInfo.patchValue({ dob: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.formGroupBasicInfo.patchValue({ dob: '' });
      }
    } else if (labelName === 'doj') {
      if (event.type === 'dateChanged') {
        this.formGroupBasicInfo.patchValue({ doj: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.formGroupBasicInfo.patchValue({ doj: '' });
      }
    } else if (labelName === 'probationPeriodEndDate') {
      if (event.type === 'dateChanged') {
        this.formGroupOthersDetails.patchValue({ probationPeriodEndDate: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.formGroupOthersDetails.patchValue({ probationPeriodEndDate: '' });
      }
    } else if (labelName === 'noticePeriodEndDate') {
      if (event.type === 'dateChanged') {
        this.formGroupOthersDetails.patchValue({ noticePeriodEndDate: event.data.formatted });
      }
      if (event.type === 'clear') {
        this.formGroupOthersDetails.patchValue({ noticePeriodEndDate: '' });
      }
    }
  }

}
