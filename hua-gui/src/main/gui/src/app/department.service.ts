import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class DepartmentService {

  constructor(private httpService: HttpService, private http: Http) { }

  getDepartments() {
    return this.httpService.callHttpGet("/resources/department")
      .map(res => res.json());
  }
}
