import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcommerceComponent } from './ecommerce.component';

const routes: Routes = [
  {
    path: '',
    component: EcommerceComponent,
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./pages/home/home.module').then((m) => m.HomeModule),
      },
      {
        path: 'producto/:id',
        loadChildren: () =>
          import('./pages/producto/producto.module').then(
            (m) => m.ProductoModule
          ),
      },
      {
        path: 'cart-unlogged',
        loadChildren: () =>
          import('./pages/cart-unlogged/cart-unlogged.module').then(
            (m) => m.CartUnloggedModule
          ),
      },
      {
        path: 'cart-logged',
        loadChildren: () =>
          import('./pages/cart-logged/cart-logged.module').then(
            (m) => m.CartLoggedModule
          ),
      },
      {
        path: 'country',
        loadChildren: () =>
          import('./pages/country/country.module').then((m) => m.CountryModule),
      },
      {
        path: 'create-user',
        loadChildren: () =>
          import('./pages/create-user/create-user.module').then(
            (m) => m.CreateUserModule
          ),
      },
      {
        path: 'payment',
        loadChildren: () =>
          import('./pages/payment/payment.module').then((m) => m.PaymentModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EcommerceRoutingModule {}
