import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyAccountRoutingModule } from './my-account-routing.module';
import { MyAccountComponent } from './my-account.component';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [
    MyAccountComponent
  ],
  imports: [
    CommonModule,
    MyAccountRoutingModule,
    SharedModule
  ]
})
export class MyAccountModule { }
