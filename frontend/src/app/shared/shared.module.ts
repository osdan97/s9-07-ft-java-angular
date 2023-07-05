import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SliderProductsComponent } from './slider-products/slider-products.component';
import { CardComponent } from './card/card.component';
import { FooterComponent } from './footer/footer.component';
import { ContentCardComponent } from './content-card/content-card.component';

@NgModule({
  declarations: [CardComponent, SliderProductsComponent, FooterComponent, ContentCardComponent],
  imports: [CommonModule, PrimeNgModule],
  exports: [CardComponent, SliderProductsComponent, FooterComponent, ContentCardComponent],
})
export class SharedModule {}
