import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class DepartmentService {

  constructor(private httpService: HttpService, private http: Http) { }

  getDepartments(unitId) {
    return this.httpService.callHttpGet("/resources/v1/department?unitId="+unitId)
      .map(res => res.json());
  }
}
