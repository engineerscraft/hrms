import {Router, RouterModule} from '@angular/router';
import {LoginFormComponent} from './login-form/login-form.component';
import {HomeComponent} from './home/home.component';
import {AccountManagementComponent} from './account-management/account-management.component';
import {EmployeeManagementComponent} from './employee-management/employee-management.component';
import {EmployeeCreationComponent} from './employee-creation/employee-creation.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {ForbiddenAccessComponent} from './forbidden-access/forbidden-access.component';
import {AuthguardService} from './authguard.service';

export const routing = RouterModule.forRoot([
    { path: '', component: LoginFormComponent},
    { path: 'home', component: HomeComponent, canActivate: [AuthguardService] },
    { path: 'accountManagement', component: AccountManagementComponent, canActivate: [AuthguardService] },
    { path: 'employeeManagement', component: EmployeeManagementComponent, canActivate: [AuthguardService] },
    { path: 'employeeCreation', component: EmployeeCreationComponent, canActivate: [AuthguardService] },
    { path: 'forbidden', component: ForbiddenAccessComponent, canActivate: [AuthguardService] },
    { path: '404', component: NotFoundComponent, canActivate: [AuthguardService] },
    { path: '**', redirectTo: '/404', canActivate: [AuthguardService] }
]);