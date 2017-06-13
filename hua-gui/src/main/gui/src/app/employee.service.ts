import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';
import { Http, Headers, RequestOptions } from '@angular/http';
import {EmptyObservable} from 'rxjs/observable/EmptyObservable';
import { FormGroup } from '@angular/forms';

@Injectable()
export class EmployeeService {

  constructor(private httpService: HttpService, private http: Http) { }

  autoComplete(name: String, value: String) {
    if(value.trim().length === 0 || name.trim().length === 0) {
      return new EmptyObservable();
    }
    return this.httpService.callHttpGet("/resources/employee?name="+name+"&value="+value)
      .map(res => res.json());
  }

  search(employee: FormGroup) {
    return this.httpService.callHttpGet("/resources/employee/search?firstName="+employee.get("firstName").value
            +"&middleName="+employee.get("middleName").value
            +"&lastName="+employee.get("lastName").value
            +"&designationId="+employee.get("designation").value
            +"&departmentId="+employee.get("department").value
            +"&collegeId="+employee.get("college").value
            +"&employeeId="+employee.get("employeeId").value
            +"&emailAddress="+employee.get("emailId").value
            +"&contactNumber="+employee.get("contactNo").value)
      .map(res => res.json());
  }
}
