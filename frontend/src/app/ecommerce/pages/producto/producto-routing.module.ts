import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductoComponent } from './producto.component';
import { DetalleProductoComponent } from '../../../shared/detalle-producto/detalle-producto.component';

const routes: Routes = [
{
  path: '',
  component: ProductoComponent,
},
{
  path: '',
  component: DetalleProductoComponent,
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductoRoutingModule { }
