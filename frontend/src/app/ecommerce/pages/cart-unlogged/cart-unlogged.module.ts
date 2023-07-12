import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { SharedModule } from "../../../shared/shared.module";
import { CartUnloggedRoutingModule } from './cart-unlogged-routing.module';
import { CartUnloggedComponent } from './cart-unlogged.component';


@NgModule({
    declarations: [
        CartUnloggedComponent
    ],
    imports: [
        CommonModule,
        CartUnloggedRoutingModule,
        SharedModule
    ]
})
export class CartUnloggedModule { }
