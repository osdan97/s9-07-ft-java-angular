import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SliderProductsComponent } from './slider-products/slider-products.component';
import { CardComponent } from './card/card.component';
import { ContentCardComponent } from './content-card/content-card.component';
import { OurServicesComponent } from './our-services/our-services.component';
import { DetalleProductoComponent } from './detalle-producto/detalle-producto.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputCartComponent } from './input-cart/input-cart.component';
import { CartComponent } from './cart/cart.component';

@NgModule({
  declarations: [
    CardComponent,
    SliderProductsComponent,
    ContentCardComponent,
    OurServicesComponent,
    DetalleProductoComponent,
    InputCartComponent,
    CartComponent,
  ],
  imports: [
    CommonModule,
    PrimeNgModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  exports: [
    CardComponent,
    SliderProductsComponent,
    ContentCardComponent,
    OurServicesComponent,
    DetalleProductoComponent,
    CartComponent,
  ],
})
export class SharedModule {}
