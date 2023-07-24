import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartLoggedRoutingModule } from './cart-logged-routing.module';
import { CartLoggedComponent } from './cart-logged.component';
import { SharedModule } from '../../../shared/shared.module';

@NgModule({
  declarations: [CartLoggedComponent],
  imports: [CommonModule, CartLoggedRoutingModule, SharedModule],
})
export class CartLoggedModule {}
