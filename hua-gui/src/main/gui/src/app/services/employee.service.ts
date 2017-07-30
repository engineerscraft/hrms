import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Injectable()
export class EmployeeService {

  constructor(private httpService: HttpService, private http: Http, private router: Router) { }

  handleError(response: Response) {
    if (response.status === 401 && response.json()["message"] !== "Refresh token expired") {
      this.router.navigate(['forbidden']);
    }
    return Observable.throw(response);;

  }

  autoComplete(name: String, value: String) {
    if (value.trim().length === 0 || name.trim().length === 0) {
      return new EmptyObservable();
    }
    return this.httpService.callHttpGet("/resources/v1/employee/management/autocomplete?attributeName=" + name + "&attributeValuePrefix=" + value)
      .map(res => res.json())
  }

  create(employee: any) {
    return this.httpService.callHttpPost("/resources/v1/employee/management", employee);
  }

  search(employeeSearchCriteria) {
    return this.httpService.callHttpGet("/resources/v1/employee/management"
      + "?firstName=" + (employeeSearchCriteria.firstName ? employeeSearchCriteria.firstName : "")
      + "&middleName=" + (employeeSearchCriteria.middleName ? employeeSearchCriteria.middleName : "")
      + "&lastName=" + (employeeSearchCriteria.lastName ? employeeSearchCriteria.lastName : "")
      + "&employeeId=" + (employeeSearchCriteria.employeeId ? employeeSearchCriteria.employeeId : "")
      + "&employeeType=" + (employeeSearchCriteria.employmentType ? employeeSearchCriteria.employmentType : "")
      + "&emailId=" + (employeeSearchCriteria.emailId ? employeeSearchCriteria.emailId : "")
      + "&orgId=" + (employeeSearchCriteria.orgId ? employeeSearchCriteria.orgId : "")
      + "&unitId=" + (employeeSearchCriteria.unitId ? employeeSearchCriteria.unitId : "")
      + "&departmentId=" + (employeeSearchCriteria.departmentId ? employeeSearchCriteria.departmentId : "")
      + "&jobRoleId=" + (employeeSearchCriteria.jobRoleId ? employeeSearchCriteria.jobRoleId : "")
      + "&designationId=" + (employeeSearchCriteria.designationId ? employeeSearchCriteria.designationId : "")
      + "&supervisorFlag=" + (employeeSearchCriteria.supervisorFlag ? employeeSearchCriteria.supervisorFlag : "")
      + "&hrFlag=" + (employeeSearchCriteria.hrFlag ? employeeSearchCriteria.hrFlag : "")
      + "&supervisorEmailId=" + (employeeSearchCriteria.supervisorEmailId ? employeeSearchCriteria.supervisorEmailId : "")
      + "&hrEmailId=" + (employeeSearchCriteria.hrEmailId ? employeeSearchCriteria.hrEmailId : "")
      + "&sex=" + (employeeSearchCriteria.sex ? employeeSearchCriteria.sex : "")
      + "&maritalStatus=" + (employeeSearchCriteria.maritalStatus ? employeeSearchCriteria.maritalStatus : "")
      + "&identityDocTypeId=" + (employeeSearchCriteria.identityDocTypeId ? employeeSearchCriteria.identityDocTypeId : "")
      + "&identityNumber=" + (employeeSearchCriteria.identityNumber ? employeeSearchCriteria.identityNumber : ""))
      .map(res => res.json())
      .catch((response: Response) => {
        return this.handleError(response);
      });

  }

  readDetails(employeeId: String) {
    return this.httpService.callHttpGet("/resources/v1/employee/management/" + employeeId)
      .map(res => res.json())
      .catch((response: Response) => {
        return this.handleError(response);
      });
  }

  uploadProfileImage(employeeId: String, reqBody: any) {
    return this.httpService.callHttpPut("/resources/v1/employee/management/" + employeeId + "/image", reqBody)
      .map(res => res.json());
  }

  updateBasicInfo(employeeId: String, reqBody: any) {
    return this.httpService.callHttpPut("/resources/v1/employee/management/" + employeeId + "/basicinfo", reqBody)
      .map(res => res.json());
  }

  getDocument(docId,employeeId) {
    return this.httpService.callHttpGet("/resources/v1/employee/management/" + employeeId + "/document/" + docId)
      .map(res => res.json())
      .catch((response: Response) => {
        return this.handleError(response);
      });
  }

  documentSave(doc, employeeId) {
    return this.httpService.callHttpPut("/resources/v1/employee/management/" + employeeId + "/document/" + doc.docId, doc)
      .map(res => res.json());
  }
}
