import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ConfirmationRoutingModule } from './confirmation-routing.module';
import { ConfirmationComponent } from './confirmation.component';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [
    ConfirmationComponent
  ],
  imports: [
    CommonModule,
    ConfirmationRoutingModule,
    SharedModule
  ]
})
export class ConfirmationModule { }
