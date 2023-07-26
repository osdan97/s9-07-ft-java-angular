import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeCountriesComponent } from './home-countries.component';

const routes: Routes = [
  { path: '',
  component: HomeCountriesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeCountriesRoutingModule { }
