import { Http, Headers, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthenticatorService {

  private authenticated = false;
  private authObservable: Observable<any>;



  constructor(private http: Http) {

  }

  authenticate(loginDetails: Object) {

    let headers = new Headers({ "Content-Type": "application/json" });
    let options = new RequestOptions({ headers: headers });

    return this.http.post("/resources/v1/authentication", JSON.stringify(loginDetails), options)
      .map(res => {
        localStorage.setItem("accessToken", res.json().accessToken);
        localStorage.setItem("refreshToken", res.json().refreshToken);
        localStorage.setItem("userName", res.json().userName);
        localStorage.setItem("userDisplayName", res.json().userDisplayName);        
      })
      .flatMap(res => {
        let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
        let options = new RequestOptions({ headers: headers });
        return this.http.get("/resources/v1/permission?permissionLevel=view", options)
        .map(res => {
          for (var i = 0; i < res.json().length; i++) {
            localStorage.setItem(res.json()[i].permissionName, "true");
          }
          return res;
        });
      });
  }

  getAuthenticationStatus() {
    return this.authenticated;
  }

  isAuthorized(permission: string) {
    return (localStorage.getItem(permission) !== null) ? true : false;

  }

  logout() {
    localStorage.clear();
  }

  setAuthenticationStatus(authenticationFlag: boolean) {
    this.authenticated = authenticationFlag;
  }
}
