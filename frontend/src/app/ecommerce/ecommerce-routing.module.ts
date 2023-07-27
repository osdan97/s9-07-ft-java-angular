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
        path: 'search',
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
        canActivate: [AuthGuard],
        canMatch: [AuthGuard],
      },
      {
        path: 'legales',
        loadChildren: () =>
          import('./pages/legales/legales.module').then((m) => m.LegalesModule),
        // canActivate: [AuthGuard],
        //canMatch: [AuthGuard],
      },
      {
        path: 'preguntas-frecuentes',
        loadChildren: () =>
          import(
            './pages/preguntas-frecuentes/preguntas-frecuentes.module'
          ).then((m) => m.PreguntasFrecuentesModule),
        // canActivate: [AuthGuard],
        // canMatch: [AuthGuard],
      },

      {
        path: 'products',
        loadChildren: () =>
          import('./pages/home-productos/home-productos.module').then(
            (m) => m.HomeProductosModule
          ),
        canActivate: [RenewSession],
      },

      {
        path: 'products/:country',
        loadChildren: () =>
          import('./pages/home-countries/home-countries.module').then(
            (m) => m.HomeCountriesModule
          ),
        canActivate: [RenewSession],
      },

      {
        path: 'terminos-condiciones',
        loadChildren: () =>
          import(
            './pages/terminos-condiciones/terminos-condiciones.module'
          ).then((m) => m.TerminosCondicionesModule),
        // canActivate: [AuthGuard],
        // canMatch: [AuthGuard],
      },
      {
        path: 'quienessomos',
        loadChildren: () =>
          import('./pages/quienessomos/quienessomos.module').then(
            (m) => m.QuienessomosModule
          ),
        //canActivate: [AuthGuard],
        //canMatch: [AuthGuard],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EcommerceRoutingModule {}
