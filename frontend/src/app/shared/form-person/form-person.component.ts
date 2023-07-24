import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import {
  FormGroup,
  Validators,
  FormBuilder,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { FormRegisterInput } from 'src/app/core/interfaces/auth.interfaces';

import { AuthService } from 'src/app/core/services/auth/auth.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-form-person',
  templateUrl: './form-person.component.html',
  styleUrls: ['./form-person.component.scss'],
})
export class FormPersonComponent {
  visible = false;

  registerForm!: FormGroup;

  formBuilder = inject(FormBuilder);
  authService = inject(AuthService);
  userService = inject(UserService);

  ngOnInit(): void {
    this.registerForm = this.initRegisterForm();
  }

  ngOnDestroy(): void {
    this.visible = false;
  }

  initRegisterForm(): FormGroup {
    return this.formBuilder.group({
      name: [''],
      lastname: [''],
      email: [''],
      password: [''],
      confirmPassword: [''],
    });
  }

  login_create() {
    if (this.registerForm.invalid) return;
  }
}
