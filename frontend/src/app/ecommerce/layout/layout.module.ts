import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [HeaderComponent, FooterComponent],
  imports: [CommonModule, PrimeNgModule, FormsModule],
  exports: [HeaderComponent, FooterComponent],
})
export class LayoutModule {}
