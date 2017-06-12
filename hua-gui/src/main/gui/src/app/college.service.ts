import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class CollegeService {

  constructor(private httpService: HttpService, private http: Http) { }

  getColleges() {
    return this.httpService.callHttpGet("/resources/college")
      .map(res => res.json());
  }

}
