import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-account-management',
  templateUrl: './account-management.component.html',
  styleUrls: ['./account-management.component.css']
})
export class AccountManagementComponent implements OnInit {

  private showContextMenu = false;
  private showCreateAccount = false;
  private mouseLocation : {left:number,top:number} = {'left':0, 'top':0};
  private formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      accountCode: ['', [Validators.required, Validators.minLength(3)]],
      accountName: ['', Validators.required],
      accountType: ['', Validators.required]
    });
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
    this.showCreateAccount=false;
    
  }

  showCreateAccountDialog() {
    this.showCreateAccount=true;
  }
}
