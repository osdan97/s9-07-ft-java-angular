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
        path: 'product/:id',
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
      {
        path: 'verify/:token',
        loadChildren: () =>
          import('./pages/verification-email/verification-email.module').then(
            (m) => m.VerificationEmailModule
          ),
      },
      {
        path: 'shipping-info',
        loadChildren: () =>
          import('./pages/shipping-info/shipping-info.module').then(
            (m) => m.ShippingInfoModule
          ),
      },
      {
        path: 'changepassword',
        loadChildren: () =>
          import('./pages/change-password/change-password.module').then(
            (m) => m.ChangePasswordModule
          ),
      },
      {
        path: 'details',
        loadChildren: () =>
          import('./pages/details/details.module').then((m) => m.DetailsModule),
      },
      {
        path: 'search-result',
        loadChildren: () =>
          import('./pages/search-result/search-result.module').then(
            (m) => m.SearchResultModule
          ),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EcommerceRoutingModule {}
