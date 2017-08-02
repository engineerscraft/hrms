import { Component, OnInit, HostListener } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import 'rxjs/add/operator/finally';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DocTypeService } from '../services/doc-type.service';
import { Observable } from 'rxjs/Observable';
import { CountryService } from '../services/country.service';
import { saveAs } from 'file-saver';


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
  private formGroupBasicInfo: FormGroup;
  private formGroupAdditionalInfo: FormGroup;
  private formGroupDocument: FormGroup;
  private identityDocTypes;
  private docTypes;
  private countries;
  private selectedDocId = 0;
  private selectedDocument = "about:blank";
  private selectedDocDetails;
  private modalDisplay = false;
  private showDocumentEdit = false;
  private documentEditFunctionInvoked = false;
  private googleDocument;
  private showUpdateMessage = false;

  constructor(private formBuilder: FormBuilder,
    private employeeService: EmployeeService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private docTypeService: DocTypeService,
    private countryService: CountryService) {
  }

  ngOnInit() {
    this.employeeInfo = this.activatedRoute.snapshot.data['employeeInfo'];
    if (this.employeeInfo === undefined) {
      this.activatedRoute
        .queryParams
        .subscribe(params => {
          this.processingInProgress = true;
          let employeeBasicInfoObservable = this.employeeService.readDetails(this.id)
            .finally(() => { this.processingInProgress = false; })
            .subscribe(data => {
              this.employeeInfo = data;
            },
            (err: any) => {
              if (err.status === 401 && err.json()['message'] !== 'Refresh token expired') {
                this.router.navigate(['forbidden']);
              }
              if (err.status === 404) {
                this.router.navigate(['404']);
              }
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
          if (err.status === 401 && err.json()['message'] !== 'Refresh token expired') {
            me.router.navigate(['forbidden']);
          }
          if (err.status === 404) {
            me.router.navigate(['404']);
          }
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
          if (err.status === 401 && err.json()["message"] !== "Refresh token expired") {
            this.router.navigate(['forbidden']);
          }
          if (err.status === 404) {
            this.router.navigate(['404']);
          }
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

  editAdditionalInfo() {
      this.showEditAdditionalInfo = true;
      this.modalDisplay = true;
  }

  getShowEditAdditionalInfo() {
    return this.showEditAdditionalInfo;
  }

  onAdditionalInfoUpdate() {
    this.processingInProgress = true;
    this.employeeService.updateAdditionalnfo(this.id, this.formGroupAdditionalInfo.value)
      .finally(() => {
        this.processingInProgress = false;
        this.showEditAdditionalInfo = false;
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
        this.employeeInfo.employeeAddlDetails = Object.assign(this.employeeInfo.employeeAddlDetails, this.formGroupAdditionalInfo.value);
      });
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
      this.modalDisplay = false;
      this.showDocumentEdit = false;
      this.documentEditFunctionInvoked = false;
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
          if (err.status === 401 && err.json()["message"] !== "Refresh token expired") {
            this.router.navigate(['forbidden']);
          }
          if (err.status === 404) {
            this.router.navigate(['404']);
          }
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

  addDocument() {

  }

  getShowDocumentEdit() {
    return this.showDocumentEdit;
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
      },
      () => {
      });
  }

  documentUpload(event) {
    console.log(event);
    this.formGroupDocument.controls['documentName'].setValue(event.srcElement.files[0].name);
    var reader = new FileReader();
    reader.readAsDataURL(event.srcElement.files[0]);
    var me = this;
    reader.onload = function () {
      me.formGroupDocument.controls['document'].setValue(reader.result);
    }
  }

  getShowSelectDocumentAlert() {
    if (this.selectedDocDetails === undefined && this.documentEditFunctionInvoked) {
      return true;
    }
    else {
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

  onClickUpdateMessageOk() {
    this.showUpdateMessage = false;
  }
}
