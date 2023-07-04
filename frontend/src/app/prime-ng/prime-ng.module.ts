import { NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';

@NgModule({
  exports: [ ButtonModule, RippleModule ],
})
export class PrimeNgModule {}
