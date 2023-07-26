import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TerminosCondicionesRoutingModule } from './terminos-condiciones-routing.module';
import { TerminosCondicionesComponent } from './terminos-condiciones.component';


@NgModule({
  declarations: [
    TerminosCondicionesComponent
  ],
  imports: [
    CommonModule,
    TerminosCondicionesRoutingModule
  ]
})
export class TerminosCondicionesModule { }
