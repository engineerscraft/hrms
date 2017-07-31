import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthguardService implements CanActivate{
  
  constructor(private router: Router) { }
  canActivate() {

    if (localStorage.getItem('accessToken')) {
      return true;
    }
    this.router.navigate(['']);
    return false;
  }
}
