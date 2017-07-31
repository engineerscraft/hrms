import {Router, RouterModule} from '@angular/router';
import {LoginFormComponent} from './login-form/login-form.component';
import {HomeComponent} from './home/home.component';
import {AccountManagementComponent} from './account-management/account-management.component';
import {EmployeeHierarchySearchComponent} from './employee-hierarchy-search/employee-hierarchy-search.component';
import {EmployeeHierarchySearchResultComponent} from './employee-hierarchy-search-result/employee-hierarchy-search-result.component';
import {EmployeeDetailsComponent} from './employee-details/employee-details.component';
import {EmployeeCreationComponent} from './employee-creation/employee-creation.component';
import {EmployeeCreationNewComponent} from './employee-creation-new/employee-creation-new.component';
import {UserDetailsComponent} from './user-details/user-details.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {ForbiddenAccessComponent} from './forbidden-access/forbidden-access.component';
import {AuthguardService} from './services/authguard.service';
import { CanDeactivateGuard } from './services/can-deactivate-guard.service';
import { EmployeeDetailsResolve } from './resolvers/employee-details.resolve';
import { EmployeeHierarchySearchResultResolve } from './resolvers/employee-hierarchy-search-result.resolve';

export const routing = RouterModule.forRoot([
    { path: '', component: LoginFormComponent},
    { path: 'home', component: HomeComponent, canActivate: [AuthguardService] },
    { path: 'accountManagement', component: AccountManagementComponent, canActivate: [AuthguardService] },
    { path: 'employeeHierarchySearch', component: EmployeeHierarchySearchComponent, canActivate: [AuthguardService] },
    { path: 'employeeHierarchySearchResult', component: EmployeeHierarchySearchResultComponent, canActivate: [AuthguardService], resolve: { searchResult: EmployeeHierarchySearchResultResolve } },
    { path: 'employeeDetails/:id', component: EmployeeDetailsComponent, canActivate: [AuthguardService], resolve: { employeeInfo: EmployeeDetailsResolve } },
    { path: 'employeeCreation', component: EmployeeCreationComponent, canActivate: [AuthguardService], canDeactivate: [CanDeactivateGuard] },
    { path: 'employeeCreationNew', component: EmployeeCreationNewComponent},
    { path: 'userDetails', component: UserDetailsComponent, canActivate: [AuthguardService] },
    { path: 'forbidden', component: ForbiddenAccessComponent, canActivate: [AuthguardService] },
    { path: '404', component: NotFoundComponent, canActivate: [AuthguardService] },
    { path: '**', redirectTo: '/404', canActivate: [AuthguardService] },
    ]);