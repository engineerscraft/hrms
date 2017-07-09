import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class JobRoleService {

  constructor(private httpService: HttpService, private http: Http) { }

  getJobRolesByOrgId(orgId) {
    return this.httpService.callHttpGet("/resources/v1/jobrole?orgId="+orgId)
      .map(res => res.json());
  }

  getSalaryByJobRoleId(jobRoleId) {
    return this.httpService.callHttpGet("/resources/v1/jobrole/" + jobRoleId + "/salary")
      .map(res => res.json());
  }

  getOptionalBenefitsByJobRoleId(jobRoleId) {
    return this.httpService.callHttpGet("/resources/v1/jobrole/" + jobRoleId + "/optbenefit")
      .map(res => res.json());
  }
}
