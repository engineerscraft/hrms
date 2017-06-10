import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class AccountService {

  constructor(private httpService: HttpService, private http: Http) { }

  getAccounts() {
    return this.httpService.callHttpGet("/resources/account")
      .map(res => res.json());
  }

  modifyAccount(accountCode: Object, account: Object) {

    return this.httpService.callHttpPut("/resources/account/"+accountCode, account)
  }

  createAccount(account: Object) {
    return this.httpService.callHttpPost("/resources/account/", account)
  }

  deleteAccount(accountCode: Object) {
    return this.httpService.callHttpDelete("/resources/account/"+accountCode)
  }
}
