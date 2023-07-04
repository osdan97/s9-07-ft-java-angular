import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SliderProductsComponent } from './slider-products/slider-products.component';
import { CardComponent } from './card/card.component';

@NgModule({
  declarations: [CardComponent, SliderProductsComponent],
  imports: [CommonModule, PrimeNgModule],
  exports: [CardComponent, SliderProductsComponent],
})
export class SharedModule {}
