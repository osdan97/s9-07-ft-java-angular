import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LegalesRoutingModule } from './legales-routing.module';
import { LegalesComponent } from './legales.component';


@NgModule({
  declarations: [
    LegalesComponent
  ],
  imports: [
    CommonModule,
    LegalesRoutingModule
  ]
})
export class LegalesModule { }
