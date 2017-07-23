import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { EmployeeService } from '../services/employee.service';

@Injectable()
export class EmployeeDetailsResolve implements Resolve<any> {

  constructor(private employeeService: EmployeeService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.employeeService.readDetails(route.paramMap.get('id'));
  }
}