import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EcommerceRoutingModule } from './ecommerce-routing.module';
import { EcommerceComponent } from './ecommerce.component';
import { LayoutModule } from './layout/layout.module';

@NgModule({
  declarations: [EcommerceComponent],
  imports: [CommonModule, EcommerceRoutingModule, LayoutModule],
})
export class EcommerceModule {}
