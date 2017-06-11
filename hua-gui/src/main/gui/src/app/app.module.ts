import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { AuthenticatorService } from './authenticator.service';
import { AccountService } from './account.service';
import { HttpService } from './http.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { AccountManagementComponent } from './account-management/account-management.component';
import { EmployeeManagementComponent } from './employee-management/employee-management.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    HomeComponent,
    SideBarComponent,
    SpinnerComponent,
    AccountManagementComponent,
    EmployeeManagementComponent
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
    HttpService,
    { provide: "windowObject", useValue: window}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
