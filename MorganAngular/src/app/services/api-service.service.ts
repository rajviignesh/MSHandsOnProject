import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SocialAuthService } from 'angularx-social-login';

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  private baseUrl = "/api/";
  private signInUrl = "user/signin";
  private signUpUrl = "user/addUser";
  private addInstUrl = "institution/create";
  private getInstUrl = "institution/get/allInsts";
  private addLocoUrl = "location/create";
  private getLocoUrl = "location/get/alllocations";
  private addCandUrl = "candidate/create";
  private getActiveCandUrl = "candidate/get/allcandidates";
  private changeStatusUrl = "candidate/updateStatus";
  private updateCandUrl = "candidate/updateCandidate";

  constructor(private http:HttpClient, private authService: SocialAuthService) { }

  signIn(UserDetails:object):Observable<any>{
    return this.http.post(this.baseUrl + this.signInUrl, UserDetails);
  }

  signUp(UserDetails:object):Observable<any>{
    return this.http.post(this.baseUrl + this.signUpUrl, UserDetails);
  }

  addInst(instDetails:object):Observable<any>{
    return this.http.post(this.baseUrl + this.addInstUrl, instDetails);
  }

  getInst():Observable<any>{
    return this.http.get(this.baseUrl + this.getInstUrl);
  }

  addLoco(locoDetails:object):Observable<any>{
    return this.http.post(this.baseUrl + this.addLocoUrl, locoDetails);
  }

  getLoco():Observable<any>{
    return this.http.get(this.baseUrl + this.getLocoUrl);
  }

  saveCandidate(candDetails:object):Observable<any>{
    return this.http.post(this.baseUrl + this.addCandUrl, candDetails);
  }

  getActiveCandidate():Observable<any>{
    return this.http.get(this.baseUrl + this.getActiveCandUrl);
  }

  changeStatus(candDetails:object):Observable<any>{
    return this.http.post(this.baseUrl + this.changeStatusUrl, candDetails)
  }

  updateCandidate(candDetails:object):Observable<any>{
    return this.http.post(this.baseUrl + this.updateCandUrl, candDetails);
  }

  signGoogleOut(): void {
    this.authService.signOut();
  }
}
