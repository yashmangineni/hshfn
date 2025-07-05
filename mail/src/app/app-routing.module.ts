import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './component/register/register.component';
import { LoginComponent } from './component/login/login.component';
import { EmployeeaddComponent } from './component/employeeadd/employeeadd.component';
import { EmployeesearchComponent } from './component/employeesearch/employeesearch.component';
import { EmployeeeditComponent } from './component/employeeedit/employeeedit.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { ViewdataComponent } from './component/viewdata/viewdata.component';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
 {  path: 'employeeadd', component: EmployeeaddComponent},
 {path:'employeesearch',component:EmployeesearchComponent},
{path:'dashboard',component:DashboardComponent},
{path:'viewdata',component:ViewdataComponent},

{path:'employeeedit/:id',component:EmployeeeditComponent} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

 }
