import { NgModule } from '@angular/core';

import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';

import { InputTextModule } from 'primeng/inputtext';
import { CarouselModule } from 'primeng/carousel';
import { PaginatorModule } from 'primeng/paginator';

import { MessagesModule } from 'primeng/messages';

@NgModule({
  exports: [
    ButtonModule,
    RippleModule,
    InputTextModule,
    CarouselModule,
    PaginatorModule,
    MessagesModule,
  ],
})
export class PrimeNgModule {}
