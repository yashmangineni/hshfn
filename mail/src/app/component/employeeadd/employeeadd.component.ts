import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/servive/service.service';

@Component({
  selector: 'app-employeeadd',
  templateUrl: './employeeadd.component.html',
  styleUrls: ['./employeeadd.component.css']
})
export class EmployeeaddComponent {
employeeForm: FormGroup;
  selectedFile!: File;

  constructor(private fb: FormBuilder, private servicee:ServiceService,private router:Router) {
    this.employeeForm = this.fb.group({
      firstName: ['', Validators.required],
      middleName: [''],
      lastName: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      department: ['', Validators.required],
      salary: ['', Validators.required],
      permanentAddress: [''],
      currentAddress: ['']
    });
  }

  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }
   onSubmit() {
    const formData = new FormData();
    formData.append('employee', new Blob([JSON.stringify(this.employeeForm.value)], { type: 'application/json' }));
    formData.append('document', this.selectedFile);

    this.servicee.addEmployee(formData).subscribe(response => {
      alert("Employee added successfully!");
      this.router.navigateByUrl('/employeesearch');

      this.employeeForm.reset();
    }, error => {
      alert("Error: " + error.error.message);
    });
  }
}
