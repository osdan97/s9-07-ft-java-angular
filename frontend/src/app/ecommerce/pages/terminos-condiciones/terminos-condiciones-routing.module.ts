import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TerminosCondicionesComponent } from './terminos-condiciones.component';

const routes: Routes = [
  {
    path:'',
    component: TerminosCondicionesComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TerminosCondicionesRoutingModule { }
