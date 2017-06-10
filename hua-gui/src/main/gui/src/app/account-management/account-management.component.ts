import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountService } from '../account.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-management',
  templateUrl: './account-management.component.html',
  styleUrls: ['./account-management.component.css'],
  host: {
    '(document:click)': 'onClick($event)',
  }
})
export class AccountManagementComponent implements OnInit {

  @ViewChild("accountCode") accountCodeInput: ElementRef;
  @ViewChild("accountName") accountNameInput: ElementRef;
  @ViewChild("accountType") accountTypeInput: ElementRef;
  @ViewChild("table") table: ElementRef;

  private accounts;
  private account = { accountCode: "", accountName: "", accountType: "" };
  private showContextMenu = false;
  private showCreateAccount = false;
  private mouseLocation: { left: number, top: number } = { 'left': 0, 'top': 0 };
  private formGroupModify: FormGroup;
  private formGroupCreate: FormGroup;
  private processingInProgress = false;
  private processingModifyInProgress = false;
  private processingCreateInProgress = false;
  private showAccountModifyPage = false;
  private showInformationMessageBox = false;
  private informationMessage = "";
  private confirmationMessage = "";
  private showConfirmationMessageBox = false;

  constructor(private formBuilder: FormBuilder, private accountService: AccountService, private router: Router) { }

  ngOnInit() {
    this.loadData();
    this.formGroupModify = this.formBuilder.group({
      accountCode: ['', [Validators.required, Validators.minLength(3)]],
      accountName: ['', [Validators.required, Validators.minLength(3)]],
      accountType: ['', [Validators.required, Validators.minLength(3)]]
    });
    this.formGroupCreate = this.formBuilder.group({
      accountCode: ['', [Validators.required, Validators.minLength(3)]],
      accountName: ['', [Validators.required, Validators.minLength(3)]],
      accountType: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  loadData() {
    this.processingInProgress = true;
    this.accountService.getAccounts()
      .subscribe(
      (res:Response) => {
        this.accounts = res;
      },
      (err: any) => {
        this.processingInProgress = false;
      },
      () => {
        this.processingInProgress = false;
      }
      );
  }

  getContextMenuCss() {
    return {
      'position': 'fixed',
      'display': this.showContextMenu ? 'block' : 'none',
      'left': this.mouseLocation.left + 'px',
      'top': this.mouseLocation.top + 'px'
    }
  }

  isProcessingInProgress() {
    return false;
  }

  getShowCreateAccount() {
    return this.showCreateAccount;
  }

  onCancelCreateAccount() {
    this.showCreateAccount = false;
    this.showAccountModifyPage = false;
  }

  showCreateAccountDialog() {
    this.showCreateAccount = true;
  }

  onRightClick(event: MouseEvent, accountCode: string, accountName: string, accountType: string) {
    this.showContextMenu = true;
    this.mouseLocation.left = event.clientX;
    this.mouseLocation.top = event.clientY;
    event.stopPropagation();
    this.account.accountCode = accountCode;
    this.account.accountName = accountName;
    this.account.accountType = accountType;

    this.formGroupModify.get("accountCode").setValue(accountCode);
    this.formGroupModify.get("accountName").setValue(accountName);
    this.formGroupModify.get("accountType").setValue(accountType);
    return false;
  }

  onClick(event: Event) {
    this.showContextMenu = false;
  }

  onModifyClick() {
    this.showAccountModifyPage = true;
  }

  getShowModifyAccount() {
    return this.showAccountModifyPage;
  }

  filterAccount() {
    var filterAccountCode = "", filterAccountName = "", filterAccountType = "", row, cellAccountCode, cellAccountName, cellAccountType, i;
    filterAccountCode = this.accountCodeInput.nativeElement.value ? this.accountCodeInput.nativeElement.value.toUpperCase() : "";
    filterAccountName = this.accountNameInput.nativeElement.value ? this.accountNameInput.nativeElement.value.toUpperCase() : "";
    filterAccountType = this.accountTypeInput.nativeElement.options[this.accountTypeInput.nativeElement.selectedIndex].text ? this.accountTypeInput.nativeElement.options[this.accountTypeInput.nativeElement.selectedIndex].text.toUpperCase() : "";

    row = this.table.nativeElement.getElementsByClassName("row");

    for (i = 0; i < row.length; i++) {
      if (i > 0) {
        cellAccountCode = row[i].getElementsByClassName("cell")[0];
        cellAccountName = row[i].getElementsByClassName("cell")[1];
        cellAccountType = row[i].getElementsByClassName("cell")[2];
        if (cellAccountCode.innerHTML.toUpperCase().indexOf(filterAccountCode) > -1
          && cellAccountName.innerHTML.toUpperCase().indexOf(filterAccountName) > -1
          && (
            (cellAccountType.innerHTML.toUpperCase().indexOf(filterAccountType) > -1) ||
            (filterAccountType === "- SELECT -")
          )
        ) {
          row[i].style.display = "";
        }
        else {
          row[i].style.display = "none";
        }
      }
    }
  }

  modify() {
    this.processingModifyInProgress = true;
    this.accountService.modifyAccount(this.formGroupModify.value.accountCode, this.formGroupModify.value)
      .subscribe(
      res => {
      },
      err => {
        this.showInformationMessageBox = true;
        this.informationMessage = "An error occured while modifying the GL Account " + this.formGroupModify.value.accountCode + " . Please try after sometime.";
        this.processingModifyInProgress = false;
        this.showAccountModifyPage = false;
      },
      () => {
        this.showInformationMessageBox = true;
        this.informationMessage = "The GL Account " + this.formGroupModify.value.accountCode + " is successfully modified.";
        this.processingModifyInProgress = false;
        this.showAccountModifyPage = false;
      });
  }

  create() {
    this.processingCreateInProgress = true;
    this.accountService.createAccount(this.formGroupCreate.value)
      .subscribe(
      res => {
      },
      err => {
        this.showInformationMessageBox = true;
        this.informationMessage = "An error occured while creating the GL Account " + this.formGroupCreate.value.accountCode + " . Please try after sometime.";
        this.processingCreateInProgress = false;
        this.showCreateAccount = false;
      },
      () => {
        this.showInformationMessageBox = true;
        this.informationMessage = "The GL Account " + this.formGroupCreate.value.accountCode + " is successfully created.";
        this.processingCreateInProgress = false;
        this.showCreateAccount = false;
      });
  }

  getShowInformationMessageBox() {
    return this.showInformationMessageBox;
  }

  OnClickMessageBoxOk() {
    this.showInformationMessageBox = false;
    this.informationMessage= "";
    this.loadData();
    this.accountCodeInput.nativeElement.value="";
    this.accountNameInput.nativeElement.value="";
    this.accountTypeInput.nativeElement.selectedIndex=0;
  }

  OnClickConfirmationBoxYes() {
    this.showConfirmationMessageBox = false;
    this.processingInProgress = true;
    this.accountService.deleteAccount(this.account.accountCode)
      .subscribe(
      res => {
      },
      err => {
        this.processingInProgress = false;
        this.showInformationMessageBox = true;
        this.informationMessage = "An error occured while deleting the GL Account " + this.account.accountCode + " . Please try after sometime.";
      },
      () => {
        this.processingInProgress = false;
        this.showInformationMessageBox = true;
        this.informationMessage = "The GL Account " + this.account.accountCode + " is successfully deleted.";
      });
  }

  OnClickConfirmationBoxNo() {
    this.showConfirmationMessageBox = false;
  }

  getShowConfirmationMessageBox() {
    return this.showConfirmationMessageBox;
  }

  onDeleteClick() {
    this.showConfirmationMessageBox = true;
    this.confirmationMessage = "Are you sure you want to delete the GL Account " + this.account.accountCode;
  }
}