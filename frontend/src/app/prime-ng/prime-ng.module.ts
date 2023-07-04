import { NgModule } from '@angular/core';

import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';

import { InputTextModule } from 'primeng/inputtext';
import { CarouselModule } from 'primeng/carousel';

@NgModule({
  exports: [ButtonModule, RippleModule, InputTextModule, CarouselModule],
})
export class PrimeNgModule {}
