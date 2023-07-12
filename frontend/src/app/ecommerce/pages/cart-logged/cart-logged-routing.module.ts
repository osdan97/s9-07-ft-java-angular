import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartLoggedComponent } from './cart-logged.component';

const routes: Routes = [
  {
    path: '',
    component: CartLoggedComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartLoggedRoutingModule { }
