import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class DocTypeService {

  constructor(private httpService: HttpService, private http: Http) {  }

  getIdentityDocTypes() {
    return this.httpService.callHttpGet("/resources/v1/doctype/identitydoctype")
      .map(res => res.json());
  }

  getDocTypes() {
    return this.httpService.callHttpGet("/resources/v1/doctype")
      .map(res => res.json());
  }
}
