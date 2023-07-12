import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { CountryRoutingModule } from './country-routing.module';
import { CountryComponent } from './country.component';


@NgModule({
  declarations: [
    CountryComponent
  ],
  imports: [
    CommonModule,
    CountryRoutingModule
  ]
})
export class CountryModule { }
