import { Component, OnInit, inject } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

import { AuthService } from 'src/app/core/services/auth/auth.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-form-casa',
  templateUrl: './form-casa.component.html',
  styleUrls: ['./form-casa.component.scss'],
})
export class FormCasaComponent implements OnInit {
  registerForm!: FormGroup;

  formBuilder = inject(FormBuilder);
  authService = inject(AuthService);
  userService = inject(UserService);

  ngOnInit(): void {
    this.registerForm = this.initRegisterForm();
  }

  initRegisterForm(): FormGroup {
    return this.formBuilder.group({
      pais: [''],
      provincia: [''],
      postal: ['', [Validators.required]],
      ciudad: ['', Validators.required],
      direccion: ['', Validators.required],
      detalle_dir: ['', Validators.required],
    });
  }

  login_create() {
    if (this.registerForm.invalid) return;
  }
}
