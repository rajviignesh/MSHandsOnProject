import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServiceService } from 'src/app/services/api-service.service';
import {ErrorStateMatcher} from '@angular/material/core';
import {  MatSnackBar } from '@angular/material/snack-bar';
import { SocialAuthService } from "angularx-social-login";
import { SocialUser , GoogleLoginProvider } from "angularx-social-login";
import { DomSanitizer } from "@angular/platform-browser";
import { MatIconRegistry } from '@angular/material/icon';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

const googleLogoURL = 
"https://raw.githubusercontent.com/fireflysemantics/logo/master/Google.svg";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  UserDetails : object = {}

  user: SocialUser;
  loggedIn: boolean;

  email = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  password = new FormControl('', [
    Validators.required
  ]);

  constructor(private api : ApiServiceService  ,private router: Router, private snackBar: MatSnackBar, private authService: SocialAuthService, private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer) {
      this.matIconRegistry.addSvgIcon(
        "logo",
        this.domSanitizer.bypassSecurityTrustResourceUrl(googleLogoURL));
     }

  ngOnInit(): void {
  }

  signin(){
    if(this.email.valid && this.password.valid){
      this.UserDetails = {
        email : this.email.value,
        password : this.password.value
      }

      this.api.signIn(this.UserDetails).subscribe({
        next: response => {
          console.log(response)
          if(response){
            sessionStorage.setItem('userData', JSON.stringify(response));
            this.router.navigate(['/user'])
          }
        },
        error: error => {
          console.log(error)
          this.snackBar.open('Invalid E-Mail/Password', 'close', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
        }
      });
    }
  }

  signinWithGoogle(){
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user != null);
      if(this.loggedIn){
        sessionStorage.setItem('userData', JSON.stringify(user));
        this.router.navigate(['/user'])
      }
    });
  }
}
