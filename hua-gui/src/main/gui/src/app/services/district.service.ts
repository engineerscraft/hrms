import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { HttpService } from './http.service';

@Injectable()
export class DistrictService {

  constructor(private httpService: HttpService) { }

  getDistricts(stateId) {
    return this.httpService.callHttpGet("/resources/v1/district?stateId="+stateId)
      .map(res => res.json());
  }

}
