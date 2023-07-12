import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { LoginCreateComponent } from './login-create/login-create.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CheckPayComponent } from './check-pay/check-pay.component';
import { PrimeNgModule } from 'src/app/prime-ng/prime-ng.module';
import { MatExpansionModule } from '@angular/material/expansion';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [LoginComponent, LoginCreateComponent, CheckPayComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    PrimeNgModule,
    MatExpansionModule,
    RouterModule,
  ],
  exports: [LoginComponent, LoginCreateComponent, CheckPayComponent],
})
export class AuthModule {}
