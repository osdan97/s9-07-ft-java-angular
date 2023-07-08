import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FinCompraRoutingModule } from './fin-compra-routing.module';
import { FinCompraComponent } from './fin-compra.component';
import { SharedModule } from "../../../shared/shared.module";


@NgModule({
    declarations: [
        FinCompraComponent
    ],
    imports: [
        CommonModule,
        FinCompraRoutingModule,
        SharedModule
    ]
})
export class FinCompraModule { }
