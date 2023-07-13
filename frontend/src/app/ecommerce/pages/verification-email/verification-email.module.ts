import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VerificationEmailRoutingModule } from './verification-email-routing.module';
import { VerificationEmailComponent } from './verification-email.component';


@NgModule({
  declarations: [
    VerificationEmailComponent
  ],
  imports: [
    CommonModule,
    VerificationEmailRoutingModule
  ]
})
export class VerificationEmailModule { }
