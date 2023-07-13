import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartUnloggedComponent } from './cart-unlogged.component';

const routes: Routes = [
  {
    path: '',
    component: CartUnloggedComponent,
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartUnloggedRoutingModule { }
