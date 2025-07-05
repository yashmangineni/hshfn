import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/servive/service.service';

@Component({
  selector: 'app-employeesearch',
  templateUrl: './employeesearch.component.html',
  styleUrls: ['./employeesearch.component.css']
})
export class EmployeesearchComponent {

 employees: any[] = [];
  keyword: string = '';
  page: number = 0;
  size: number = 5;
  totalPages: number = 0;

  constructor(private service: ServiceService,private router:Router) {}

  ngOnInit() {
    this.searchEmployees();
  }

  searchEmployees() {
    this.service.searchEmployees(this.keyword, this.page, this.size).subscribe(
      (res) => {
        this.employees = res.content;
        this.totalPages = res.totalPages;
      },
      (err) => {
        console.error('Search failed', err);
      }
    );
  }

  onPageChange(newPage: number) {
    this.page = newPage;
    this.searchEmployees();
  }
  onSearchInputChange(event: any) {
    this.page = 0; // Reset to first page when keyword changes
    this.searchEmployees();
  }
  editEmployee(id: number) {
    this.router.navigate(['/employeeedit', id]); // navigate to edit page with employee id
  }

  deleteEmployee(id: number) {
  if(confirm("Are you sure you want to delete this employee?")) {
    this.service.deleteEmployee(id).subscribe(() => {
      alert('Employee deleted successfully!');
      this.searchEmployees(); // reload the list after deletion
    }, error => {
      console.error('Delete failed', error);
      alert('Failed to delete employee.');
    });
  }
}


}
