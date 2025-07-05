import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
constructor(private router: Router) {}

  goToAddEmployee() {
    this.router.navigate(['/employeeadd']);
  }
  goToViewEmployee() {
    this.router.navigateByUrl('/viewdata'); // route for viewing employee list or view page
  }
}
