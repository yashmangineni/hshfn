import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from 'src/app/servive/service.service';

@Component({
  selector: 'app-employeeedit',
  templateUrl: './employeeedit.component.html',
  styleUrls: ['./employeeedit.component.css']
})
export class EmployeeeditComponent {
employeeForm: FormGroup;
  id!: number;

  constructor(
    private route: ActivatedRoute,
    private service: ServiceService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.employeeForm = this.fb.group({
      firstName: ['', Validators.required],
      middleName: [''],
      lastName: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      department: ['', Validators.required],
      salary: ['', [Validators.required, Validators.min(0)]],
      permanentAddress: [''],
      currentAddress: [''],
      // add other fields if necessary
    });
  }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.service.getEmployeeById(this.id).subscribe({
      next: (emp) => {
        this.employeeForm.patchValue(emp);
      },
      error: (err) => {
        alert('Error loading employee data');
      }
    });
  }

  onUpdate() {
  if (this.employeeForm.valid) {
    this.service.updateEmployee(this.id, this.employeeForm.value).subscribe(
      res => {
        alert('Employee updated successfully!');
        this.router.navigate(['/employeesearch']); // or wherever
      },
      err => {
        console.error('Update failed:', err);
        alert('Failed to update employee.');
      }
    );
  }
}
  
}
