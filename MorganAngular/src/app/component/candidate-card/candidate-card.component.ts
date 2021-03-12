import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router , NavigationExtras } from '@angular/router';
import { candidate } from 'src/app/model/candidate';
import { ApiServiceService } from 'src/app/services/api-service.service';

@Component({
  selector: 'app-candidate-card',
  templateUrl: './candidate-card.component.html',
  styleUrls: ['./candidate-card.component.css']
})
export class CandidateCardComponent implements OnInit {

  @Input() activeCand : candidate;
  candDetail : object = {};
  
  constructor(private api : ApiServiceService, private snackBar: MatSnackBar, private router: Router ) { }

  ngOnInit(): void {
    console.log(this.activeCand)
  }

  changeStatus(){
    this.candDetail = {
      email : this.activeCand.email
    }
    console.log(this.candDetail)
    this.api.changeStatus(this.candDetail).subscribe({
      next: response => {
        console.log(response)
        if(response){
          this.snackBar.open('Status Changed Successfully!', 'close', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
          this.activeCand.status = !this.activeCand.status;
        }
      },
      error: error => {
        console.log(error)
        this.snackBar.open('Change Failed, Check Details Entered!', 'close', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top'
        });
      }
    });
  }

  updateCandidate(){
    let navigationExtras: NavigationExtras = {
      queryParams: {
          "cand" : JSON.stringify(this.activeCand)
      }
  };
    this.router.navigate(['../user/updateCandidate'], navigationExtras )
  }
}
