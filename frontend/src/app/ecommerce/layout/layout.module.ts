import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NavbarCategoriesComponent } from './header/components/navbar-categories/navbar-categories.component';

@NgModule({
  declarations: [HeaderComponent, FooterComponent, NavbarCategoriesComponent],
  imports: [CommonModule, PrimeNgModule, FormsModule, RouterModule],
  exports: [HeaderComponent, FooterComponent],
})
export class LayoutModule {}
