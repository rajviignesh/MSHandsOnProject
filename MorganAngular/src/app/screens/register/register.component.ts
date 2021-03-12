import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServiceService } from 'src/app/services/api-service.service';
import {ErrorStateMatcher} from '@angular/material/core';
import {  MatSnackBar } from '@angular/material/snack-bar';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  UserDetails : object = {}

  username = new FormControl('', [
    Validators.required
  ]);

  email = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  password = new FormControl('', [
    Validators.required
  ]);


  constructor(private api : ApiServiceService  ,private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  signup(){
    if(this.email.valid && this.password.valid){
      this.UserDetails = {
        name :   this.username.value,
        email : this.email.value,
        password : this.password.value
      }

      this.api.signUp(this.UserDetails).subscribe({
        next: response => {
          console.log(response)
          if(response){
            this.snackBar.open('Registration Completed Successfully!', 'close', {
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'top'
            });
            this.router.navigate(['/login'])
          }
        },
        error: error => {
          console.log(error)
          this.snackBar.open('Registration Failed, Check Details Entered!', 'close', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
        }
      });
    }
  }
}


