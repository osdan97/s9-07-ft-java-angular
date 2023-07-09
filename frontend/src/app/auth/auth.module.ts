import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { LoginCreateComponent } from './login-create/login-create.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [LoginComponent, LoginCreateComponent],
  imports: [CommonModule, ReactiveFormsModule],
  exports: [LoginComponent, LoginCreateComponent],
})
export class AuthModule {}
