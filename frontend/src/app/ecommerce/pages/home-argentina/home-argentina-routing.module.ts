import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeArgentinaComponent } from './home-argentina.component';

const routes: Routes = [
  { path: '',
  component: HomeArgentinaComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeArgentinaRoutingModule { }
