import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcommerceComponent } from './ecommerce.component';
import { AuthGuard } from '../core/guards/auth/auth.guard';
import { CartUnloguedGuard } from '../core/guards/cart-unlogued/cart-unlogued.guard';
import { CartLoguedGuard } from '../core/guards/cart-logued/cart-logued.guard';

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
        path: 'product/:id', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/producto/producto.module').then(
            (m) => m.ProductoModule
          ),
      },
      {
        path: 'cart-unlogged', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/cart-unlogged/cart-unlogged.module').then(
            (m) => m.CartUnloggedModule
          ),
        canActivate: [CartUnloguedGuard],
        canMatch: [CartUnloguedGuard],
      },
      {
        path: 'cart-logged', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/cart-logged/cart-logged.module').then(
            (m) => m.CartLoggedModule
          ),
        canActivate: [AuthGuard, CartLoguedGuard],
        canMatch: [AuthGuard, CartLoguedGuard],
      },
      {
        path: 'country',
        loadChildren: () =>
          import('./pages/country/country.module').then((m) => m.CountryModule),
      },
      {
        path: 'create-user', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/create-user/create-user.module').then(
            (m) => m.CreateUserModule
          ),
      },
      {
        path: 'payment',
        loadChildren: () =>
          import('./pages/payment/payment.module').then((m) => m.PaymentModule),
        canActivate: [AuthGuard],
        canMatch: [AuthGuard],
      },
      {
        path: 'verify/:token', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/verification-email/verification-email.module').then(
            (m) => m.VerificationEmailModule
          ),
      },
      {
        path: 'shipping-info', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/shipping-info/shipping-info.module').then(
            (m) => m.ShippingInfoModule
          ),
      },
      {
        path: 'changepassword', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/change-password/change-password.module').then(
            (m) => m.ChangePasswordModule
          ),
      },
      {
        path: 'details',
        loadChildren: () =>
          import('./pages/details/details.module').then((m) => m.DetailsModule),
        canActivate: [AuthGuard],
        canMatch: [AuthGuard],
      },
      {
        path: 'confirmation',
        loadChildren: () =>
          import('./pages/confirmation/confirmation.module').then(
            (m) => m.ConfirmationModule
          ),
        canActivate: [AuthGuard],
        canMatch: [AuthGuard],
      },
      {
        path: 'search-result',
        loadChildren: () =>
          import('./pages/search-result/search-result.module').then(
            (m) => m.SearchResultModule
          ),
      },
      {
        path: 'my-account',
        loadChildren: () =>
          import('./pages/my-account/my-account.module').then(
            (m) => m.MyAccountModule
          ),
        canActivate: [AuthGuard],
        canMatch: [AuthGuard],
      },
      {
        path: 'home-productos',
        loadChildren: () =>
          import('./pages/home-productos/home-productos.module').then(
            (m) => m.HomeProductosModule
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
