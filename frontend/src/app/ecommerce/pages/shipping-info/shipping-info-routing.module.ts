import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShippingInfoComponent } from './shipping-info.component';

const routes: Routes = [
  {
    path:'',
    component: ShippingInfoComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShippingInfoRoutingModule { }
