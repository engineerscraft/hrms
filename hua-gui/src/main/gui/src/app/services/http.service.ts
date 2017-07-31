import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/mergeMap';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { AuthenticatorService } from './authenticator.service';
import 'rxjs/add/observable/of';
import { Router } from '@angular/router';

@Injectable()
export class HttpService {

  constructor(private http: Http, private authenticatorService: AuthenticatorService, private router: Router) { }

  private token: { accessToken: string, refreshToken: string };

  callHttpGet(url: string) {
    let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
    let options = new RequestOptions({ headers: headers });

    var me = this;
    return this.http.get(url, options)
      .catch(initialError => {
        if (initialError && initialError.status === 401 && initialError.json()["message"]==="Token expired") {
          // token might be expired, try to refresh token
          var tokenObject = new Object();
          tokenObject["token"] = localStorage.getItem("refreshToken");
          return me.authenticatorService.authenticate(tokenObject)
            .flatMap(res => {

                let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
                let options = new RequestOptions({ headers: headers });
                return me.http.get(url, options);
            })
            .catch(error => {
              if (error && error.status === 401 && error.json()["message"]==="Refresh token expired") {
                this.router.navigate(['']);
                localStorage.clear();
                return Observable.throw(error);
              }
              else {
                return Observable.throw(error);
              }
            });
        }
        else {
          return Observable.throw(initialError);
        }
      });
  }


  callHttpPut(url: string, body: object) {
    let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
    let options = new RequestOptions({ headers: headers });

    var me = this;
    return this.http.put(url, body, options)
      .catch(initialError => {
        if (initialError && initialError.status === 401 && initialError.json()["message"]==="Token expired") {
          // token might be expired, try to refresh token
          var tokenObject = new Object();
          tokenObject["token"] = localStorage.getItem("refreshToken");
          return me.authenticatorService.authenticate(tokenObject)
            .flatMap(res => {
                let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
                let options = new RequestOptions({ headers: headers });
                return me.http.put(url, body, options);
              })
            .catch(error => {
              if (error && error.status === 401 && error.json()["message"]==="Refresh token expired") {
                localStorage.clear();
                this.router.navigate(['']);
                return Observable.throw(error);
              }
              else {
                return Observable.throw(error);
              }
            });
        }
        else {
          return Observable.throw(initialError);
        }
      });
  }


callHttpDelete(url: string) {
    let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
    let options = new RequestOptions({ headers: headers });

    var me = this;
    return this.http.delete(url, options)
      .catch(initialError => {
        if (initialError && initialError.status === 401 && initialError.json()["message"]==="Token expired") {
          // token might be expired, try to refresh token
          var tokenObject = new Object();
          tokenObject["token"] = localStorage.getItem("refreshToken");
          return me.authenticatorService.authenticate(tokenObject)
            .flatMap(res => {
                let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
                let options = new RequestOptions({ headers: headers });
                return me.http.delete(url, options);
              })
            .catch(error => {
              if (error && error.status === 401 && error.json()["message"]==="Refresh token expired") {
                localStorage.clear();
                this.router.navigate(['']);
                return Observable.throw(error);
              }
              else {
                return Observable.throw(error);
              }
            });
        }
        else {
          return Observable.throw(initialError);
        }
      });
  }

callHttpPost(url: string, body: object) {
    let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
    let options = new RequestOptions({ headers: headers });

    var me = this;
    return this.http.post(url, body, options)
      .catch(initialError => {
        if (initialError && initialError.status === 401 && initialError.json()["message"]==="Token expired") {
          // token might be expired, try to refresh token
          var tokenObject = new Object();
          tokenObject["token"] = localStorage.getItem("refreshToken");
          return me.authenticatorService.authenticate(tokenObject)
            .flatMap(res => {
                let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
                let options = new RequestOptions({ headers: headers });
                return me.http.post(url, body, options);
              })
            .catch(error => {
              if (error && error.status === 401 && error.json()["message"]==="Refresh token expired") {
                localStorage.clear();
                this.router.navigate(['']);
                return Observable.throw(error);
              }
              else {
                return Observable.throw(error);
              }
            });
        }
        else {
          return Observable.throw(initialError);
        }
      });
  }
}