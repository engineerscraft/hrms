import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticatorService } from '../authenticator.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  private open = false;

  constructor(private router: Router, private authenticatorService: AuthenticatorService) { }

  ngOnInit() {

  }

  changeClass() {
    if(this.open === false)
      this.open = true;
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
    window.scrollTo(0,0);
    this.router.navigate(['changePassword']);
    this.open = false;
  }
}
