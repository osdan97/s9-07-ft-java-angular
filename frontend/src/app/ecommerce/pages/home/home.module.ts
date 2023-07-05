import { SharedModule } from './../../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { CategoriesComponent } from './component/categories/categories.component';
import { NewsComponent } from './component/news/news.component';

@NgModule({
  declarations: [HomeComponent, CategoriesComponent, NewsComponent],
  imports: [CommonModule, HomeRoutingModule, PrimeNgModule, SharedModule],
})
export class HomeModule {}
