import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuienessomosComponent } from './quienessomos.component';
const routes: Routes = [
  { path: '',
  component:  QuienessomosComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuienessomosRoutingModule { }
