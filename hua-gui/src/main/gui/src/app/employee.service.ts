import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class EmployeeService {

  constructor(private httpService: HttpService, private http: Http) { }

  autoComplete(name, value) {
    return this.httpService.callHttpGet("/resources/employee?name="+name+",value="+value)
      .map(res => res.json());
  }
}
