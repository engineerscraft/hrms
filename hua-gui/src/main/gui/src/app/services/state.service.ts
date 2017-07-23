import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { HttpService } from './http.service';

@Injectable()
export class StateService {

  constructor(private httpService: HttpService) { }

  getStates(countryId) {
    return this.httpService.callHttpGet("/resources/v1/state?countryId="+countryId)
      .map(res => res.json());
  }
}
