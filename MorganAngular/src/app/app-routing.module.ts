import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginGuard } from './auth/login.guard';
import { UpdateCandidateComponent } from './component/update-candidate/update-candidate.component';
import { AddCandidateComponent } from './screens/add-candidate/add-candidate.component';
import { LogInComponent } from './screens/log-in/log-in.component'
import { NotFoundComponent } from './screens/not-found/not-found.component';
import { RegisterComponent } from './screens/register/register.component'
import { TrendsComponent } from './screens/trends/trends.component';
import { UserScreenComponent } from './screens/user-screen/user-screen.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user', canActivate: [LoginGuard] , component : UserScreenComponent},
  {path : 'user/addCandidate',canActivate: [LoginGuard] , component : AddCandidateComponent},
  {path : 'user/updateCandidate', canActivate: [LoginGuard] , component : UpdateCandidateComponent},
  {path : 'user/trends', canActivate: [LoginGuard] , component : TrendsComponent},
  {path : '404notfound' , component : NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
