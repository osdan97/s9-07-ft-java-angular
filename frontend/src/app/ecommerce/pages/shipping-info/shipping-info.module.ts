import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShippingInfoRoutingModule } from './shipping-info-routing.module';
import { ShippingInfoComponent } from './shipping-info.component';


@NgModule({
  declarations: [
    ShippingInfoComponent
  ],
  imports: [
    CommonModule,
    ShippingInfoRoutingModule
  ]
})
export class ShippingInfoModule { }
