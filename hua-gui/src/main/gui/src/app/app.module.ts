import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { UserService } from './user.service';
import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { AuthenticatorService } from './authenticator.service';
import { AccountService } from './account.service';
import { CollegeService } from './college.service';
import { EmployeeService } from './employee.service';
import { DepartmentService } from './department.service';
import { OrganizationService } from './organization.service';
import { UnitService } from './unit.service';
import { DocTypeService } from './doc-type.service';
import { JobRoleService } from './job-role.service';
import { DesignationService } from './designation.service';
import { HttpService } from './http.service';
import { AuthguardService } from './authguard.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { AccountManagementComponent } from './account-management/account-management.component';
import { EmployeeCreationComponent } from './employee-creation/employee-creation.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { ForbiddenAccessComponent } from './forbidden-access/forbidden-access.component';
import { EmployeeHierarchySearchComponent } from './employee-hierarchy-search/employee-hierarchy-search.component';
import { CountryService } from './country.service';
import { EmployeeHierarchySearchResultComponent } from './employee-hierarchy-search-result/employee-hierarchy-search-result.component';
import { DistrictService } from './district.service';
import { StateService } from './state.service';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';

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
    EmployeeDetailsComponent
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
    { provide: "windowObject", useValue: window}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
