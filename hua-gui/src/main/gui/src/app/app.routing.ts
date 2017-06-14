import {Router, RouterModule} from '@angular/router';
import {LoginFormComponent} from './login-form/login-form.component';
import {HomeComponent} from './home/home.component';
import {AccountManagementComponent} from './account-management/account-management.component';
import {EmployeeManagementComponent} from './employee-management/employee-management.component';
import {EmployeeCreationComponent} from './employee-creation/employee-creation.component';

export const routing = RouterModule.forRoot([
    { path: '', component: LoginFormComponent},
    { path: 'home', component: HomeComponent},
    { path: 'accountManagement', component: AccountManagementComponent},
    { path: 'employeeManagement', component: EmployeeManagementComponent},
    { path: 'employeeCreation', component: EmployeeCreationComponent},
]);