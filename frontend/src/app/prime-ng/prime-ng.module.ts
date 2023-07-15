import { NgModule } from '@angular/core';

import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';

import { InputTextModule } from 'primeng/inputtext';
import { CarouselModule } from 'primeng/carousel';
import { PaginatorModule } from 'primeng/paginator';

import { MessagesModule } from 'primeng/messages';

import { DialogModule } from 'primeng/dialog';

import { AccordionModule } from 'primeng/accordion';

import { RadioButtonModule } from 'primeng/radiobutton';


@NgModule({
  exports: [
    ButtonModule,
    RippleModule,
    InputTextModule,
    CarouselModule,
    PaginatorModule,
    MessagesModule,
    DialogModule,
    AccordionModule,
    RadioButtonModule,
  ],
})
export class PrimeNgModule {}
