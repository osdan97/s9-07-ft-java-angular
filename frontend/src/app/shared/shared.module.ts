import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SliderProductsComponent } from './slider-products/slider-products.component';
import { CardComponent } from './card/card.component';
import { FooterComponent } from './footer/footer.component';
import { ContentCardComponent } from './content-card/content-card.component';
import { OurServicesComponent } from './our-services/our-services.component';

@NgModule({
  declarations: [
    CardComponent,
    SliderProductsComponent,
    FooterComponent,
    ContentCardComponent,
    OurServicesComponent,
  ],
  imports: [CommonModule, PrimeNgModule],
  exports: [
    CardComponent,
    SliderProductsComponent,
    FooterComponent,
    ContentCardComponent,
    OurServicesComponent,
  ],
})
export class SharedModule {}
