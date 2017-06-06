import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';

@Injectable()
export class ParameterService {

  constructor(private httpService: HttpService) { }

  getParameters() {
    return this.httpService.callHttp("/resources/parameter")
      .map(res => res.json());
  }
}
