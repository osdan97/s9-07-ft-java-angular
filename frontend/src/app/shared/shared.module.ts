import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SliderProductsComponent } from './slider-products/slider-products.component';
import { CardComponent } from './card/card.component';
import { ContentCardComponent } from './content-card/content-card.component';
import { OurServicesComponent } from './our-services/our-services.component';
import { DetalleProductoComponent } from './detalle-producto/detalle-producto.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputCartComponent } from './input-cart/input-cart.component';
import { CartComponent } from './cart/cart.component';
import { FormAddressComponent } from './form-address/form-address.component';
import { StockPipe } from './pipes/stock.pipe';
import { ImageByCountryPipe } from './pipes/image-by-country.pipe';
import { FormPersonComponent } from './form-person/form-person.component';
import { FormCasaComponent } from './form-casa/form-casa.component';
import { QuantityPipe } from './pipes/quantity.pipe';
import { CategoriesComponent } from './categories/categories.component';
import { AuthModule } from '../auth/auth.module';
import { PaisesComponent } from './paises/paises.component';

@NgModule({
  declarations: [
    CardComponent,
    SliderProductsComponent,
    ContentCardComponent,
    OurServicesComponent,
    DetalleProductoComponent,
    InputCartComponent,
    CartComponent,
    FormAddressComponent,
    StockPipe,
    ImageByCountryPipe,
    FormPersonComponent,
    FormCasaComponent,
    QuantityPipe,
    CategoriesComponent,
    PaisesComponent,
  ],
  imports: [
    CommonModule,
    PrimeNgModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    AuthModule,
  ],
  exports: [
    CardComponent,
    SliderProductsComponent,
    ContentCardComponent,
    OurServicesComponent,
    DetalleProductoComponent,
    CartComponent,
    FormAddressComponent,
    FormPersonComponent,
    FormCasaComponent,
    CategoriesComponent,
    QuantityPipe,
    PaisesComponent,
  ],
})
export class SharedModule {}
