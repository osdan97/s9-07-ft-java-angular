import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetailsRoutingModule } from './details-routing.module';
import { DetailsComponent } from './details.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';

@NgModule({
  declarations: [DetailsComponent],
  imports: [
    CommonModule,
    DetailsRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    PrimeNgModule,
  ],
})
export class DetailsModule {}
