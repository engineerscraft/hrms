import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { EmployeeService } from '../services/employee.service';

@Injectable()
export class EmployeeHierarchySearchResultResolve implements Resolve<any> {

    private employeeSearchCriteria = { firstName: "", middleName: "", lastName: "", employeeId: "", employmentType: "", emailId: "", orgId: "", unitId: "", departmentId: "", jobRoleId: "", designationId: "", supervisorFlag: "", hrFlag: "", supervisorEmailId: "", hrEmailId: "", sex: "", identityDocTypeId: "", identityNumber: "" };

    constructor(private employeeService: EmployeeService) { }

    resolve(route: ActivatedRouteSnapshot) {

        this.employeeSearchCriteria.firstName = route.queryParamMap.get('firstName');
        this.employeeSearchCriteria.middleName = route.queryParamMap.get('middleName');
        this.employeeSearchCriteria.lastName = route.queryParamMap.get('lastName');
        this.employeeSearchCriteria.employeeId = route.queryParamMap.get('employeeId');
        this.employeeSearchCriteria.employmentType = route.queryParamMap.get('employmentType');
        this.employeeSearchCriteria.emailId = route.queryParamMap.get('emailId');
        this.employeeSearchCriteria.orgId = route.queryParamMap.get('orgId');
        this.employeeSearchCriteria.unitId = route.queryParamMap.get('unitId');
        this.employeeSearchCriteria.departmentId = route.queryParamMap.get('departmentId');
        this.employeeSearchCriteria.jobRoleId = route.queryParamMap.get('jobRoleId');
        this.employeeSearchCriteria.designationId = route.queryParamMap.get('designationId');
        this.employeeSearchCriteria.supervisorFlag = route.queryParamMap.get('supervisorFlag');
        this.employeeSearchCriteria.hrFlag = route.queryParamMap.get('hrFlag');
        this.employeeSearchCriteria.supervisorEmailId = route.queryParamMap.get('supervisorEmailId');
        this.employeeSearchCriteria.hrEmailId = route.queryParamMap.get('hrEmailId');
        this.employeeSearchCriteria.sex = route.queryParamMap.get('sex');
        this.employeeSearchCriteria.identityDocTypeId = route.queryParamMap.get('identityDocTypeId');
        this.employeeSearchCriteria.identityNumber = route.queryParamMap.get('identityNumber');

        return this.employeeService.search(this.employeeSearchCriteria);
    }
}