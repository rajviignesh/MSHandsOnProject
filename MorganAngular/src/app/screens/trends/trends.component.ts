import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { candidate } from 'src/app/model/candidate';
import { ApiServiceService } from 'src/app/services/api-service.service';

@Component({
  selector: 'app-trends',
  templateUrl: './trends.component.html',
  styleUrls: ['./trends.component.css']
})
export class TrendsComponent implements OnInit {

  activeCandDetails : candidate[] = [];
  instList = [];
  locoList = [];
  skillList = [];

  locoNumber = [];
  instNumber = [];
  skillNumber = [];

  graphDataList = [];

  
  constructor(private api : ApiServiceService, private router : Router) { }

  ngOnInit(): void {

    this.api.getInst().subscribe({
      next: response => {
        if(response){
          response.forEach(element => {
            this.instList.push(element.instName);
            this.instNumber.push(0);
          });
        }
      },
      error: error => {
        console.log(error)
      }
    });

    this.api.getLoco().subscribe({
      next: response => {
        if(response){
          response.forEach(element => {
            this.locoList.push(element.locationName);
            this.locoNumber.push(0);
          });
        }
      },
      error: error => {
        console.log(error)
      }
    });

    this.api.getActiveCandidate().subscribe({
      next: response => {
        if(response){
          response.forEach(element => {
            this.activeCandDetails.push(element);
            element.skillSet.forEach(elem => {
              if(!this.skillList.includes(elem.skillName)){
                this.skillList.push(elem.skillName)
                this.skillNumber.push(1)
              }
              else{
                this.skillNumber[this.skillList.findIndex(x => x ===  elem.skillName)] += 1
              }
            });
            this.instList.forEach( (ele, ind) => {
              if(ele === element.instData.instName){
                this.instNumber[ind] += 1;
              }
            } )
            this.locoList.forEach( (ele, ind) => {
              if(ele === element.locoData.locationName){
                this.locoNumber[ind] += 1;
              }
            } )
          });
        }
      },
      error: error => {
        console.log(error)
      }
    });

    this.graphDataList.push(
      {
        name: "Institution Trends",
        graphx : this.instList,
        graphy : this.instNumber,
        type : "bar" 
      },
      {
        name: "Location Trends",
        graphx : this.locoList,
        graphy : this.locoNumber,
        type : "bar" 
      },
      {
        name: "Skill Trends",
        graphx : this.skillList,
        graphy : this.skillNumber,
        type : "pie" 
      }
    )

  }

  logout(){
    sessionStorage.clear()
    this.api.signGoogleOut();
    this.router.navigate(['/login'])
  }


}
