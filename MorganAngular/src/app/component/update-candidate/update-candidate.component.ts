import { Component, OnInit } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { trigger, state, style, animate, transition} from '@angular/animations';
import {  MatSnackBar } from '@angular/material/snack-bar';
import { ApiServiceService } from 'src/app/services/api-service.service';
import { Observable } from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { ActivatedRoute, Router } from '@angular/router';
import { MatStepper } from '@angular/material/stepper';
import { candidate } from 'src/app/model/candidate';

export interface Skill {
  name: string;
}


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-update-candidate',
  animations: [
    trigger('openClose', [
      state('open', style({
        height: 200,
        opacity: 1
      })),
      state('closed', style({
        height: 0,
        opacity: 0
      })),
      transition('open => closed', [
        animate('0.2s')
      ]),
      transition('closed => open', [
        animate('0.2s')
      ]),
    ]),
  ],
  templateUrl: './update-candidate.component.html',
  styleUrls: ['./update-candidate.component.css']
})
export class UpdateCandidateComponent implements OnInit {

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  instToggle = false;
  locoToggle = false;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  skillChips: Skill[] = [];
  skillNames = [];
  instDetails : object = {};
  locoDetails : object = {};
  candDetails : object = {};
  instId : any;
  locoId : any;
  filteredInstOptions: Observable<any[]>;
  filteredLocoOptions: Observable<any[]>;
  instDetailList = [];
  locoDetailList = [];
  instNameList : string[] = [];
  locoNameList : string[] = [];
  currentCandidate : candidate;

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  matcher = new MyErrorStateMatcher();
  
  firstName = new FormControl('', [
    Validators.required
  ]);
  lastName = new FormControl('', [
    Validators.required
  ]);
  email = new FormControl({value : '', disabled: true}, [
    Validators.required,
    Validators.email
  ]);

  inst = new FormControl('', [
    Validators.required
  ]);
  instName = new FormControl('', [
    Validators.required
  ]);
  instCity = new FormControl('', [
    Validators.required
  ]);
  instPincode = new FormControl('', [
    Validators.required,
    Validators.maxLength(6)
  ]);
  loco = new FormControl('', [
    Validators.required
  ]);
  locoName = new FormControl('', [
    Validators.required
  ]);
  locoCity = new FormControl('', [
    Validators.required
  ]);
  locoPincode = new FormControl('', [
    Validators.required,
    Validators.maxLength(6)
  ]);


  constructor(private _formBuilder: FormBuilder, private api : ApiServiceService, private snackBar: MatSnackBar, private route: ActivatedRoute, private router: Router ) {
    this.route.queryParams.subscribe(params => {
      this.currentCandidate = JSON.parse(params["cand"]);
  });
   }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.api.getInst().subscribe({
      next: response => {
        if(response){
          response.forEach(element => {
            this.instDetailList.push(element);
            this.instNameList.push(element.instName)
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
            this.locoDetailList.push(element);
            this.locoNameList.push(element.locationName)
          });
        }
      },
      error: error => {
        console.log(error)
      }
    });

    this.filteredInstOptions = this.inst.valueChanges.pipe(startWith(''),map(value => this._filterInst(value)) );
    this.filteredLocoOptions = this.loco.valueChanges.pipe(startWith(''),map(value => this._filterLoco(value)) );

    console.log(this.currentCandidate);
    this.firstName.setValue(this.currentCandidate.firstName);
    this.lastName.setValue(this.currentCandidate.lastName);
    this.email.setValue(this.currentCandidate.email);
    this.currentCandidate.skillSet.forEach(element => {
      this.skillChips.push({name : element.skillName});
      this.skillNames.push(element.skillName);
    });
    this.inst.setValue(this.currentCandidate.instData.instName);
    this.instId = this.currentCandidate.instData.instId;
    this.loco.setValue(this.currentCandidate.locoData.locationName);
    this.locoId = this.currentCandidate.locoData.locationId;
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.skillChips.push({name: value.trim()});
      this.skillNames.push(value.trim())
    }

    if (input) {
      input.value = '';
    }
  }

  remove(chip: Skill): void {
    const index = this.skillChips.indexOf(chip);

    if (index >= 0) {
      this.skillChips.splice(index, 1);
      this.skillNames.splice(index, 1);
      console.log(this.skillNames)
    }
  }

  private _filterInst(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.instNameList.filter(option => option.toLowerCase().includes(filterValue));
  }

  private _filterLoco(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.locoNameList.filter(option => option.toLowerCase().includes(filterValue));
  }

  addInst(){
    this.instToggle = !this.instToggle
  }

  addLoco(){
    this.locoToggle = !this.locoToggle
  }

  onInstChanged(event: MatAutocompleteSelectedEvent) {
    this.instDetailList.forEach( element => {
      if(element.instName == event.option.value){
        this.instId = element.instId;
      }
    });
  }

    onLocoChanged(event: MatAutocompleteSelectedEvent) {
      this.locoDetailList.forEach( element => {
        if(element.locationName == event.option.value){
          this.locoId = element.locationId;
        }
      });
    
  }

  saveInst(stepper: MatStepper){
    if(this.instName.valid){
      this.instDetails = {
        instName :   this.instName.value,
        city : this.instCity.value,
        pincode : this.instPincode.value
      }

      this.api.addInst(this.instDetails).subscribe({
        next: response => {
          console.log(response)
          if(response){
            this.snackBar.open('Institution Saved Successfully!', 'close', {
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'top'
            });
            this.instId = response.instId;
            stepper.next();
            console.log(this.instId)
          }
        },
        error: error => {
          console.log(error)
          this.snackBar.open('Save Failed, Check Details Entered!', 'close', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
        }
      });
    }
  }

  saveLoco(stepper: MatStepper){
    if(this.locoName.valid){
      this.locoDetails = {
        locationName :   this.locoName.value,
        city : this.locoCity.value,
        pincode : this.locoPincode.value
      }

      this.api.addLoco(this.locoDetails).subscribe({
        next: response => {
          console.log(response)
          if(response){
            this.snackBar.open('Location Saved Successfully!', 'close', {
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'top'
            });
            this.locoId = response.locationId;
            stepper.next();
            console.log(this.locoId)
          }
        },
        error: error => {
          console.log(error)
          this.snackBar.open('Save Failed, Check Details Entered!', 'close', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
        }
      });
    }
  }

  saveCandidate(){ 
      this.candDetails = {
        firstName :   this.firstName.value,
        lastName :   this.lastName.value,
        email : this.email.value,
        skillList : this.skillNames,
        locationId : this.locoId,
        instId : this.instId
      }
      console.log(this.candDetails);
      this.api.updateCandidate(this.candDetails).subscribe({
        next: response => {
          console.log(response)
          if(response){
            this.snackBar.open('Candidate Saved Successfully!', 'close', {
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'top'
            });
            this.router.navigate(['../user'])
          }
        },
        error: error => {
          console.log(error)
          this.snackBar.open('Save Failed, Check Details Entered!', 'close', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
        }
      });
    }
    
}
