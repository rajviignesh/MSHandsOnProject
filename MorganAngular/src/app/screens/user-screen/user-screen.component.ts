import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {PageEvent} from "@angular/material/paginator";
import { Router } from '@angular/router';
import { candidate } from 'src/app/model/candidate';
import { ApiServiceService } from 'src/app/services/api-service.service';

@Component({
  selector: 'app-user-screen',
  templateUrl: './user-screen.component.html',
  styleUrls: ['./user-screen.component.css']
})
export class UserScreenComponent implements OnInit {
  
  Username : string | any; 
  pageIndex:number = 0;
  pageSize:number = 4;
  lowValue:number = 0;
  highValue:number = 4; 
  activeCandDetails : candidate[] = [];
  defaultActiveCandDetails : candidate[] = [];
  searchText : string = '';
  institution = new FormControl();
  instList: string[] = [];
  location = new FormControl();
  locoList: string[] = [];

  constructor(private router: Router, private api : ApiServiceService) { }

  ngOnInit(): void {
    let currentUser = JSON.parse(sessionStorage.getItem('userData'));
    this.Username = currentUser.name;

    this.api.getActiveCandidate().subscribe({
      next: response => {
        if(response){
          response.forEach(element => {
            this.activeCandDetails.push(element);
          });
        }
      },
      error: error => {
        console.log(error)
      }
    });
    this.defaultActiveCandDetails = this.activeCandDetails;
    this.api.getInst().subscribe({
      next: response => {
        if(response){
          response.forEach(element => {
            this.instList.push(element.instName);
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
          });
        }
      },
      error: error => {
        console.log(error)
      }
    });
  }

  getPaginatorData(event  : PageEvent) : PageEvent{
    if(event.pageIndex === this.pageIndex + 1){
       this.lowValue = this.lowValue + this.pageSize;
       this.highValue =  this.highValue + this.pageSize;
      }
   else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
     }   
      this.pageIndex = event.pageIndex;
      return event;
}

onFilterSearch(){
  this.activeCandDetails = this.defaultActiveCandDetails;
  let instArr = this.institution.value;
  let locoArr = this.location.value;
  
  if(this.institution.value && (instArr.length != 0)){
    let newArray = this.activeCandDetails.filter(function (el) {
      return instArr.includes(el.instData.instName);
    });
    this.activeCandDetails = newArray;
  }

  if(this.location.value && (locoArr.length != 0)){
    let newArray = this.activeCandDetails.filter(function (el) {
      return locoArr.includes(el.locoData.locationName);
    });
    this.activeCandDetails = newArray;
  }
  }

clearFilter(){
  this.activeCandDetails = this.defaultActiveCandDetails;
  this.location.reset();
  this.institution.reset();
  
}
logout(){
  sessionStorage.clear()
  this.api.signGoogleOut();
  this.router.navigate(['/login'])
}

}
