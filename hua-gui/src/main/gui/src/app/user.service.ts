import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';


@Injectable()
export class UserService {

  constructor(private httpService: HttpService, private http: Http) { }

  changePassword(body: object) {
    return this.httpService.callHttpPost("/resources/v1/changepassword", body);
  }
}
