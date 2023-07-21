import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeProductosComponent } from './home-productos.component';

const routes: Routes = [
  {
    path: '',
    component: HomeProductosComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeProductosRoutingModule { }
