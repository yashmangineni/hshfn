import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeaddComponent } from './component/employeeadd/employeeadd.component';
import { EmployeesearchComponent } from './component/employeesearch/employeesearch.component';
import { EmployeedetailsComponent } from './component/employeedetails/employeedetails.component';
import { EmployeeeditComponent } from './component/employeeedit/employeeedit.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { ViewdataComponent } from './component/viewdata/viewdata.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    EmployeeaddComponent,
    EmployeesearchComponent,
    EmployeedetailsComponent,
    EmployeeeditComponent,
    DashboardComponent,
    ViewdataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
