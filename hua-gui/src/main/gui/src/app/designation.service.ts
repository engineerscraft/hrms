import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class DesignationService {

  constructor(private httpService: HttpService, private http: Http) { }

  getDesignations() {
    return this.httpService.callHttpGet("/resources/designation")
      .map(res => res.json());
  }
}
