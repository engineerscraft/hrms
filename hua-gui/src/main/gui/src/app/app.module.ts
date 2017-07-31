import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { UserService } from './services/user.service';
import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { AuthenticatorService } from './services/authenticator.service';
import { AccountService } from './services/account.service';
import { CollegeService } from './services/college.service';
import { EmployeeService } from './services/employee.service';
import { DepartmentService } from './services/department.service';
import { OrganizationService } from './services/organization.service';
import { UnitService } from './services/unit.service';
import { DocTypeService } from './services/doc-type.service';
import { JobRoleService } from './services/job-role.service';
import { DesignationService } from './services/designation.service';
import { HttpService } from './services/http.service';
import { AuthguardService } from './services/authguard.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { AccountManagementComponent } from './account-management/account-management.component';
import { EmployeeCreationComponent } from './employee-creation/employee-creation.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { ForbiddenAccessComponent } from './forbidden-access/forbidden-access.component';
import { EmployeeHierarchySearchComponent } from './employee-hierarchy-search/employee-hierarchy-search.component';
import { CountryService } from './services/country.service';
import { EmployeeHierarchySearchResultComponent } from './employee-hierarchy-search-result/employee-hierarchy-search-result.component';
import { DistrictService } from './services/district.service';
import { StateService } from './services/state.service';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { DatePickerComponent } from './date-picker/date-picker.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { EmployeeDetailsResolve } from './resolvers/employee-details.resolve';
import { EmployeeHierarchySearchResultResolve } from './resolvers/employee-hierarchy-search-result.resolve';
import { EmployeeCreationNewComponent } from './employee-creation-new/employee-creation-new.component';
import { DialogService } from './services/dialog.service';
import { CanDeactivateGuard } from './services/can-deactivate-guard.service';
import { SafePipe } from './pipes/safe.pipe';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    HomeComponent,
    SideBarComponent,
    SpinnerComponent,
    AccountManagementComponent,
    EmployeeCreationComponent,
    NotFoundComponent,
    ForbiddenAccessComponent,
    EmployeeHierarchySearchComponent,
    EmployeeHierarchySearchResultComponent,
    EmployeeDetailsComponent,
    DatePickerComponent,
    UserDetailsComponent,
    EmployeeCreationNewComponent,
    SafePipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    routing,
    BrowserAnimationsModule
  ],
  providers: [
    DialogService,
    AuthenticatorService,
    AccountService,
    CollegeService,
    EmployeeService,
    DepartmentService,
    DesignationService,
    HttpService,
    UserService,
    AuthguardService,
    OrganizationService,
    UnitService,
    DocTypeService,
    JobRoleService,
    CountryService,
    StateService, 
    DistrictService,
    EmployeeDetailsResolve,
    EmployeeHierarchySearchResultResolve,
    { provide: "windowObject", useValue: window}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
