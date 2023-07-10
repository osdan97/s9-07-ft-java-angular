import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductoRoutingModule } from './producto-routing.module';
import { ProductoComponent } from './producto.component';
import { SharedModule } from 'src/app/shared/shared.module';




@NgModule({
  declarations: [
    ProductoComponent
  ],
  imports: [
    CommonModule,
    ProductoRoutingModule,
    SharedModule
    
  ]
})
export class ProductoModule { }
