import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class UnitService {

  constructor(private httpService: HttpService, private http: Http) { }

  getUnits(orgId) {
    return this.httpService.callHttpGet("/resources/v1/unit?organizationId="+orgId)
      .map(res => res.json());
  }
}
