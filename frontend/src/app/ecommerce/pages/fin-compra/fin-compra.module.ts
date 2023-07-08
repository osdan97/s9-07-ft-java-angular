import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FinCompraRoutingModule } from './fin-compra-routing.module';
import { FinCompraComponent } from './fin-compra.component';


@NgModule({
  declarations: [
    FinCompraComponent
  ],
  imports: [
    CommonModule,
    FinCompraRoutingModule
  ]
})
export class FinCompraModule { }
