import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class OrganizationService {

  constructor(private httpService: HttpService, private http: Http) { }

  getOrganizations() {
    return this.httpService.callHttpGet("/resources/v1/organization")
      .map(res => res.json());
  }
}
