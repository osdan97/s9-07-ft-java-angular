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
  selector: 'app-login-create',
  templateUrl: './login-create.component.html',
  styleUrls: ['./login-create.component.scss'],
})
export class LoginCreateComponent implements OnInit, OnDestroy {
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
    return this.formBuilder.group(
      {
        name: ['', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]],
        lastname: [
          '',
          [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)],
        ],
        email: [
          '',
          [
            Validators.required,
            Validators.pattern(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/),
          ],
        ],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      },
      {
        validators: [this.camposIguales('password', 'confirmPassword')],
      }
    );
  }

  camposIguales(campo1: string, campo2: string) {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const pass1 = formGroup.get(campo1)?.value;
      const pass2 = formGroup.get(campo2)?.value;

      if (pass1 !== pass2) {
        formGroup.get(campo2)?.setErrors({ noIguales: true });
        return { noIguales: true };
      }

      formGroup.get(campo2)?.setErrors(null);
      return null;
    };
  }

  login_create() {
    if (this.registerForm.invalid) return;

    const registerBody: FormRegisterInput = {
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
      name: this.registerForm.value.name,
      lastName: this.registerForm.value.lastname,
    };

    this.authService.register(registerBody).subscribe({
      next: (res) => {
        this.visible = true;
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  closeModal() {
    this.visible = false;
  }
}
