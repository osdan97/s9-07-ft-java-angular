import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeCountriesRoutingModule } from './home-countries-routing.module';
import { HomeCountriesComponent } from './home-countries.component';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    HomeCountriesComponent
  ],
  imports: [
    CommonModule,
    HomeCountriesRoutingModule,
    PrimeNgModule,
    SharedModule
  ]
})
export class HomeCountriesModule { }
