import { Component, OnInit, Inject, animate, state, style, transition, trigger } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticatorService } from '../authenticator.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomValidator } from '../validators/custom-validators';
import { UserService } from '../user.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
  animations: [
    trigger('heroState', [
      state('inactive', style({
        height: '0',
        paddingTop: '0',
        paddingBottom: '0',
        marginTop: '0',
        marginBottom: '0',
        visibility: 'hidden',
        overflowY: 'hidden'
      })),
      state('active', style({

      })),
      transition('inactive => active', animate('300ms ease-in')),
      transition('active => inactive', animate('300ms ease-out'))
    ])
  ]
})
export class SideBarComponent implements OnInit {

  private statusMessage = 'inactive';

  private formGroup: FormGroup;

  private processingInProgess = false;

  private open = false;

  private showChangePassword = false;

  private responseMessage       = '';
  private status : boolean      = true;

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
        validator: this.matchingPasswords('newPassword', 'confirmedPassword')
      }
    );
  }

  matchingPasswords(passwordKey: string, confirmPasswordKey: string) {
    return (group: FormGroup): { [key: string]: any } => {
      let password = group.controls[passwordKey];
      let confirmPassword = group.controls[confirmPasswordKey];

      if (password.value !== confirmPassword.value) {
        return {
          mismatchedPasswords: true
        };
      }
    }
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
    this.authenticatorService.logout();
    this.router.navigate(['']);
  }

  isAuthorized(permission: string) {
    return this.authenticatorService.isAuthorized(permission);
  }

  getUserName() {
    return localStorage.getItem("userDisplayName") + " (" + localStorage.getItem("userName") + ")";
  }

  onClickChangePassword() {
    this.responseMessage          = '';
    this.status                   = true;
    this.open                     = false;
    this.showChangePassword       = true;
    this.statusMessage            = 'inactive';
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

  onClickEmployeeSearch() {
    window.scrollTo(0, 0);
    this.router.navigate(['employeeManagement']);
    this.open = false;
  }

  onClickEmployeeCreation() {
    window.scrollTo(0, 0);
    this.router.navigate(['employeeCreation']);
    this.open = false;
  }

  onChangePassword() {
    this.responseMessage          = '';
    this.status                   = true;
    this.statusMessage            = 'inactive';
    this.processingInProgess      = true;
    var response: any ;
    this.userService.changePassword(this.formGroup.value)
      .subscribe(
      res => {
        console.log(res);
        response                      = res;
      },
      err => {
        this.responseMessage          = 'There was an error while performing the request';
        this.status                   = false;
      },
      () => {
        if(response){
          if( response.STATUS === 'ERROR')
            this.status               = false;
          else
            this.status               = true;
          this.responseMessage        = response.MESSAGE;
        }
      });

    this.statusMessage = 'active';
        setTimeout(() => {
          if (this.statusMessage === 'active') {
            this.statusMessage = 'inactive';
            if(this.status){
              this.showChangePassword = false;
            }
          }
        }, this.status? 3000: 5000);
        this.processingInProgess = false;
  }

   getStatus() {
    return this.statusMessage;
  }
}
