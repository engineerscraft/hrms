import { Component, OnInit, Inject, animate, state, style, transition, trigger } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticatorService } from '../services/authenticator.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomValidator } from '../validators/custom-validators';
import { UserService } from '../services/user.service';
import { HostListener } from '@angular/core';
import 'rxjs/add/observable/throw';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  @HostListener('window:keydown', ['$event'])
    keyboardInput(event: KeyboardEvent) {
      console.log(`${event.keyCode}`)
      if(event.keyCode === 77 && event.ctrlKey && !this.open){
        this.changeClass();
      }else if(event.keyCode === 27 && this.open === true){
        this.changeClass();
      }
  }

  private formGroup: FormGroup;

  private processingInProgess = false;

  private open = false;

  private showChangePassword = false;

  private changePasswordMessage = '';

  private showLogoutMessage = false;

  constructor(private router: Router, private authenticatorService: AuthenticatorService, private formBuilder: FormBuilder,
  private userService: UserService) { }

  ngOnInit() {
    this.createFormGroup();
  }


  createFormGroup() {
    this.formGroup = this.formBuilder.group({
      currentPassword:  ['', Validators.compose([Validators.required, CustomValidator.noSpace])],
      newPassword:      ['', Validators.compose([Validators.required, Validators.minLength(6), CustomValidator.noSpace])],
      confirmedPassword: ['',Validators.compose([Validators.required, Validators.minLength(6)])]
    }, {
        validator: this.matchingPasswords
      }
    );
  }

  matchingPasswords(group: FormGroup) {
      let password = group.controls['newPassword'];
      let confirmPassword = group.controls['confirmedPassword'];
      if (password.value !== confirmPassword.value) {
        confirmPassword.setErrors({matchingPasswords: true});
      }
      return null;
  }



  changeClass() {
    if (this.open === false) {
      this.open = true;
      this.showChangePassword = false;
    }
    else
      this.open = false;
  }

  getOpen() {
    return this.open;
  }

  onClickParameter() {
    window.scrollTo(0, 0);
    this.router.navigate(['parameter']);
    this.open = false;
  }

  goHome() {
    window.scrollTo(0, 0);
    this.router.navigate(['home']);
    this.open = false;
  }

  logout() {
    this.processingInProgess = true;
    this.authenticatorService.logout();
    this.processingInProgess = false;
    this.router.navigate(['']);
  }

  isAuthorized(permission: string) {
    return this.authenticatorService.isAuthorized(permission);
  }

  getUserName() {
    return localStorage.getItem("userDisplayName") + " (" + localStorage.getItem("userName") + ")";
  }

  onClickChangePassword() {
    this.open                     = false;
    this.showChangePassword       = true;
    this.createFormGroup();
  }

  getShowChangePassword() {
    return this.showChangePassword;
  }

  isProcessingInProgress() {
    return this.processingInProgess;
  }

  onCancelChangePassword() {
    this.showChangePassword = false;
  }

  onClickAccountMgmt() {
    window.scrollTo(0, 0);
    this.router.navigate(['accountManagement']);
    this.open = false;
  }

  onClickEmployeeHierarchySearch() {
    window.scrollTo(0, 0);
    this.router.navigate(['employeeHierarchySearch']);
    this.open = false;
  }

  onClickEmployeeCreation() {
    window.scrollTo(0, 0);
    this.router.navigate(['employeeCreation']);
    this.open = false;
  }

  onClickUserDetails() {
    window.scrollTo(0, 0);
    this.router.navigate(['userDetails']);
    this.open = false;
  }

  onChangePassword() {
    this.processingInProgess      = true;
    this.userService.changePassword(this.formGroup.value)
      .subscribe(
      res => {
        this.processingInProgess = false;
        this.changePasswordMessage = "Password changed successfully";
        this.showChangePassword = false;
      },
      err => {
        this.changePasswordMessage = err.json()["message"];
        this.processingInProgess = false;
        this.showChangePassword = false;
      });
  }

  OnClickOk() {
    this.changePasswordMessage ='';
  }

  getErrorClass(formControlName) {
    if(this.formGroup.controls[formControlName].invalid && this.formGroup.controls[formControlName].touched) 
      return ["error-bar"];
    else
      return ["bar"];
  }

  isFieldValueMissing(formControlName) {
    if(this.formGroup.controls[formControlName].hasError('required') && this.formGroup.controls[formControlName].touched) 
      return true;
    else
      return false;
  }

  isConfirmedPasswordNotMatching() {
    if(this.formGroup.controls['newPassword'].valid && this.formGroup.controls['confirmedPassword'].touched 
            && this.formGroup.controls['confirmedPassword'].invalid) {
      return true;
    }
    else
      return false;
  }

  isValueInvalid(formControlName) {
    if(this.formGroup.controls[formControlName].invalid && !this.isFieldValueMissing(formControlName) && this.formGroup.controls[formControlName].touched) {
      return true;
    }
    else {
      return false;
    }
  }

  getShowLogoutMessage() {
    return this.showLogoutMessage;
  }

  onClickLogout() {
    this.open              = false;
    this.showLogoutMessage = true;
  }

  onCancelLogout() {
    this.showLogoutMessage = false;
  }

}
