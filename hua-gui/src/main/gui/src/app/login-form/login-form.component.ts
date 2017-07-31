import { animate, Component, OnInit, state, style, transition, trigger } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticatorService } from '../services/authenticator.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
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
export class LoginFormComponent implements OnInit {

  private loginErrorMessage = 'inactive';
  private formGroup: FormGroup;
  private processingInProgess = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private authenticator: AuthenticatorService) { }

  ngOnInit() {

    if(localStorage.getItem("accessToken")) {
      this.router.navigate(["home"]);
    }

    this.formGroup = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', Validators.required]
    });
  }

  onClick() {
    this.processingInProgess = true;
    this.authenticator.authenticate(this.formGroup.value)
      .subscribe(
      res => {
      },
      err => {
        localStorage.clear();
        this.loginErrorMessage = 'active';
        setTimeout(() => {
          if (this.loginErrorMessage === 'active') {
            this.loginErrorMessage = 'inactive';
          }
        }, 3000);
        this.processingInProgess = false;
      },
      () => {
        this.authenticator.setAuthenticationStatus(true);
        this.router.navigate(['home']);
        this.processingInProgess = false;
      });
  }

  getLoginStatus() {
    return this.loginErrorMessage;
  }

  isProcessingInProgress() {
    return this.processingInProgess;
  }
}
