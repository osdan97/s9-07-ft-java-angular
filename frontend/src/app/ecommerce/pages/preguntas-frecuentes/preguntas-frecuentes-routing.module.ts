import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PreguntasFrecuentesComponent } from './preguntas-frecuentes.component';

const routes: Routes = [
  {
    path:'',
    component: PreguntasFrecuentesComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreguntasFrecuentesRoutingModule { }
