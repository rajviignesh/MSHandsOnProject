import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
 
import * as PlotlyJS from 'plotly.js/dist/plotly.js';
import { PlotlyModule } from 'angular-plotly.js';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogInComponent } from './screens/log-in/log-in.component';
import { RegisterComponent } from './screens/register/register.component';
import { AngularMaterialModule } from './angular-material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserScreenComponent } from './screens/user-screen/user-screen.component';
import { AddCandidateComponent } from './screens/add-candidate/add-candidate.component';
import { CandidateCardComponent } from './component/candidate-card/candidate-card.component';
import { UpdateCandidateComponent } from './component/update-candidate/update-candidate.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { TrendsComponent } from './screens/trends/trends.component';
import { ExpandableGraphComponent } from './component/expandable-graph/expandable-graph.component';
import { NotFoundComponent } from './screens/not-found/not-found.component';
import {SocialAuthServiceConfig ,SocialLoginModule, GoogleLoginProvider} from 'angularx-social-login'

PlotlyModule.plotlyjs = PlotlyJS;



@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    RegisterComponent,
    UserScreenComponent,
    AddCandidateComponent,
    CandidateCardComponent,
    UpdateCandidateComponent,
    TrendsComponent,
    ExpandableGraphComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FlexLayoutModule,
    FormsModule, 
    HttpClientModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule, 
    AngularMaterialModule,
    CommonModule, 
    PlotlyModule,
    BrowserAnimationsModule,
    SocialLoginModule 
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '221774539152-e1md4r5l6u6te2cpra0r3aub63np734k.apps.googleusercontent.com'
            )
          }
        ]
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
