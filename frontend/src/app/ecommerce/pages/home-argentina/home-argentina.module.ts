import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeArgentinaRoutingModule } from './home-argentina-routing.module';
import { HomeArgentinaComponent } from './home-argentina.component';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    HomeArgentinaComponent
  ],
  imports: [
    CommonModule,
    HomeArgentinaRoutingModule,
    PrimeNgModule,
    SharedModule
  ]
})
export class HomeArgentinaModule { }
