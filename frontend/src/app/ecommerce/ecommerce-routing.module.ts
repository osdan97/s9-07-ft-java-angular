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
        path: 'fin-compra',
        loadChildren: () =>
          import('./pages/fin-compra/fin-compra.module').then(
            (m) => m.FinCompraModule
          ),
      },
      {
        path: 'pais',
        loadChildren: () =>
          import('./pages/pais/pais.module').then(
            (m) => m.PaisModule
          ),
      },
      
      {
        path: 'create-user',
        loadChildren: () =>
          import('./pages/create-user/create-user.module').then(
            (m) => m.CreateUserModule
          )
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EcommerceRoutingModule {}
