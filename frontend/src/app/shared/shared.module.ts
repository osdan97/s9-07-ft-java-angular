import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SliderProductsComponent } from './slider-products/slider-products.component';
import { CardComponent } from './card/card.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [CardComponent, SliderProductsComponent, FooterComponent],
  imports: [CommonModule, PrimeNgModule],
  exports: [CardComponent, SliderProductsComponent, FooterComponent],
})
export class SharedModule {}
