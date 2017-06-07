import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticatorService } from '../authenticator.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  private formGroup: FormGroup;

  private open = false;

  private showChangePassword = false;

  constructor(private router: Router, private authenticatorService: AuthenticatorService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      currentPassword: ['', [Validators.required, Validators.minLength(3)]],
      newPassword: ['', Validators.required],
      confirmedPassword: ['', Validators.required]
    });
  }

  changeClass() {
    if(this.open === false)
    {
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
    window.scrollTo(0,0);
    this.router.navigate(['parameter']);
    this.open = false;
  }

  goHome() {
    window.scrollTo(0,0);
    this.router.navigate(['home']);
    this.open = false;
  }

  logout() {
    this.authenticatorService.logout();
    this.router.navigate(['']);
  }

  isAuthorized(permission: string) {
    return this.authenticatorService.isAuthorized(permission);
  }

  getUserName() {
    return localStorage.getItem("userDisplayName")+" ("+localStorage.getItem("userName")+")";
  }

  onClickChangePassword() {
    this.open = false;
    this.showChangePassword = true;
  }

  getShowChangePassword() {
    return this.showChangePassword;
  }

  isProcessingInProgress() {
    return false;
  }

  onCancelChangePassword() {
    this.showChangePassword = false;
  }

  onClickAccountMgmt() {
    window.scrollTo(0,0);
    this.router.navigate(['accountManagement']);
    this.open = false;
  }
}
