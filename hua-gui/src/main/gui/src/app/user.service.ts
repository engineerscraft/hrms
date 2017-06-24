import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';


@Injectable()
export class UserService {

  constructor(private httpService: HttpService, private http: Http) { }

  changePassword(body: object) {
    console.log('Calling method');
    return this.httpService.callHttpPost("/resources/changepassword", body)
      .map(res => res.json());
  }
}
