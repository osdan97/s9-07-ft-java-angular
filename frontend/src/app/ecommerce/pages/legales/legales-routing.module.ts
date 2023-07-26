import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LegalesComponent } from './legales.component';

const routes: Routes = [
  {
    path:'',
    component: LegalesComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LegalesRoutingModule { }
