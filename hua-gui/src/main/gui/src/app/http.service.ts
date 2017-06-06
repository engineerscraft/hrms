import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/mergeMap';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { AuthenticatorService } from './authenticator.service';

@Injectable()
export class HttpService {

  constructor(private http: Http, private authenticatorService: AuthenticatorService) { }

  private token: { accessToken: string, refreshToken: string };

  callHttp(url: string) {
    let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
    let options = new RequestOptions({ headers: headers });

    var me = this;
    return this.http.get(url, options)
      .catch(initialError => {
        if (initialError && initialError.status === 401) {
          // token might be expired, try to refresh token
          var tokenObject = new Object();
          tokenObject["token"] = localStorage.getItem("refreshToken");
          return me.authenticatorService.authenticate(tokenObject)
            .flatMap(res => {
              if (res.status == 200) {
                let headers = new Headers({ "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("accessToken") });
                let options = new RequestOptions({ headers: headers });
                return me.http.get(url, options);
              }
              return Observable.throw(initialError);
            });
        }
        else {
          return Observable.throw(initialError);
        }
      });
  }
}
