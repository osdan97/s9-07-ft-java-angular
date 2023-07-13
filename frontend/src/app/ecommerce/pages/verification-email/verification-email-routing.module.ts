import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VerificationEmailComponent } from './verification-email.component';

const routes: Routes = [
  {
    path: '',
    component: VerificationEmailComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VerificationEmailRoutingModule {}
