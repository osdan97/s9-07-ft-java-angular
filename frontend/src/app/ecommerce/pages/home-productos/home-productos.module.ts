import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeProductosRoutingModule } from './home-productos-routing.module';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { HomeProductosComponent } from './home-productos.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    HomeProductosComponent,
    
  
  ],
  imports: [
    CommonModule,
    HomeProductosRoutingModule,
    PrimeNgModule,
    SharedModule,
    
  ]
})
export class HomeProductosModule { }
