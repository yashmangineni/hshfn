import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/servive/service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
formVisible: boolean = true;
  successMessage: string = '';
  errorMessage: string = '';
 // alert1: string;

  constructor(private _formBuilder: FormBuilder, private service:ServiceService, private _router: Router,private cdr: ChangeDetectorRef) {}

  registration = this._formBuilder.group({
    email: ['', [
      Validators.required,
      Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    ]],
    password: ['', [
      Validators.required,
      Validators.minLength(5),
      
    ]]
  });

  data() {
    if (this.registration.invalid) {  
      this.errorMessage = '❌ Please fill out all fields correctly!';
      this.successMessage = '';  
      return;
    }
  
    this.service.adduser(this.registration.value).subscribe(
      (response) => {
        this.successMessage = '✅ Signup Successful!';
        this.errorMessage = '';
        this.cdr.detectChanges();  // ✅ Forces UI update
  
        // ✅ Wait 2 seconds before navigating
        setTimeout(() => {
          this.registration.reset();
          this._router.navigate(['login']);
        }, 2000);
      },
      (error) => {
        console.error('Signup Error:', error);
        this.successMessage = ''; 
        if (error.status === 400) {
          this.errorMessage = '❌ Invalid data. Please check your inputs!';
        } else if (error.status === 409) {
          this.errorMessage = '❌ User already exists. Try another email!';
        } else if (error.status === 500) {
          this.errorMessage = '❌ Server error. Try again later!';
        } else {
          this.errorMessage = '❌ There was an error during signup. Please try again later.';
        }
      }
    );
  }
}
