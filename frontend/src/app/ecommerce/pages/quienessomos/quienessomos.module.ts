import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuienessomosRoutingModule } from './quienessomos-routing.module';
import { QuienessomosComponent } from './quienessomos.component';


@NgModule({
  declarations: [
    QuienessomosComponent
  ],
  imports: [
    CommonModule,
    QuienessomosRoutingModule
  ]
})
export class QuienessomosModule { }
