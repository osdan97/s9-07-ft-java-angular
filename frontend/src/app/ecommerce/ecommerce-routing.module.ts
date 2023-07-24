import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcommerceComponent } from './ecommerce.component';
import { AuthGuard } from '../core/guards/auth/auth.guard';
import { CartUnloguedGuard } from '../core/guards/cart-unlogued/cart-unlogued.guard';
import { CartLoguedGuard } from '../core/guards/cart-logued/cart-logued.guard';
import { RenewSession } from '../core/guards/renewSession/renew-session.guard';

const routes: Routes = [
  {
    path: '',
    component: EcommerceComponent,
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./pages/home/home.module').then((m) => m.HomeModule),
        canActivate: [RenewSession],
      },
      {
        path: 'product/:id', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/producto/producto.module').then(
            (m) => m.ProductoModule
          ),
        canActivate: [RenewSession],
      },
      {
        path: 'cart-unlogged', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/cart-unlogged/cart-unlogged.module').then(
            (m) => m.CartUnloggedModule
          ),
        canActivate: [CartUnloguedGuard, RenewSession],
        canMatch: [CartUnloguedGuard],
      },
      {
        path: 'cart-logged', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/cart-logged/cart-logged.module').then(
            (m) => m.CartLoggedModule
          ),
        canActivate: [AuthGuard, CartLoguedGuard, RenewSession],
        canMatch: [AuthGuard, CartLoguedGuard],
      },
      {
        path: 'country',
        loadChildren: () =>
          import('./pages/country/country.module').then((m) => m.CountryModule),
        canActivate: [RenewSession],
      },
      {
        path: 'create-user', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/create-user/create-user.module').then(
            (m) => m.CreateUserModule
          ),
        canActivate: [RenewSession],
      },
      {
        path: 'payment',
        loadChildren: () =>
          import('./pages/payment/payment.module').then((m) => m.PaymentModule),
        canActivate: [AuthGuard, RenewSession],
        canMatch: [AuthGuard],
      },
      {
        path: 'verify/:token', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/verification-email/verification-email.module').then(
            (m) => m.VerificationEmailModule
          ),
        canActivate: [RenewSession],
      },
      {
        path: 'shipping-info', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/shipping-info/shipping-info.module').then(
            (m) => m.ShippingInfoModule
          ),
        canActivate: [RenewSession],
      },
      {
        path: 'changepassword', //!Ya esta en alta fidelidad
        loadChildren: () =>
          import('./pages/change-password/change-password.module').then(
            (m) => m.ChangePasswordModule
          ),
        canActivate: [RenewSession],
      },
      {
        path: 'details',
        loadChildren: () =>
          import('./pages/details/details.module').then((m) => m.DetailsModule),
        canActivate: [AuthGuard, RenewSession],
        canMatch: [AuthGuard],
      },
      {
        path: 'confirmation',
        loadChildren: () =>
          import('./pages/confirmation/confirmation.module').then(
            (m) => m.ConfirmationModule
          ),
        canActivate: [AuthGuard, RenewSession],
        canMatch: [AuthGuard],
      },
      {
        path: 'search-result',
        loadChildren: () =>
          import('./pages/search-result/search-result.module').then(
            (m) => m.SearchResultModule
          ),
        canActivate: [RenewSession],
      },
      {
        path: 'my-account',
        loadChildren: () =>
          import('./pages/my-account/my-account.module').then(
            (m) => m.MyAccountModule
          ),
        canActivate: [AuthGuard, RenewSession],
        canMatch: [AuthGuard],
      },
      {
        path: 'home-productos',
        loadChildren: () =>
          import('./pages/home-productos/home-productos.module').then(
            (m) => m.HomeProductosModule
          ),
        canActivate: [RenewSession],
      },

      {
        path: 'home-argentina',
        loadChildren: () =>
          import('./pages/home-argentina/home-argentina.module').then(
            (m) => m.HomeArgentinaModule
          ),
        canActivate: [AuthGuard, RenewSession],
        canMatch: [AuthGuard],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EcommerceRoutingModule {}
