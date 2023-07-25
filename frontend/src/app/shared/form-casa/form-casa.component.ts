import { Component, OnInit, inject, Output, EventEmitter } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

import { AuthService } from 'src/app/core/services/auth/auth.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-form-casa',
  templateUrl: './form-casa.component.html',
  styleUrls: ['./form-casa.component.scss'],
})
export class FormCasaComponent implements OnInit {
  registerForm!: FormGroup;
  userData!: any;
  @Output() regreso = new EventEmitter<boolean>();

  formBuilder = inject(FormBuilder);
  authService = inject(AuthService);
  userService = inject(UserService);
  cookieService = inject(CookieService);

  ngOnInit(): void {
    this.userData = JSON.parse(localStorage.getItem('userData') || '{}');
    this.registerForm = this.initRegisterForm();
  }

  initRegisterForm(): FormGroup {
    return this.formBuilder.group({
      pais: ['', [Validators.required]],
      provincia: ['', [Validators.required]],
      postal: ['', [Validators.required]],
      ciudad: ['', Validators.required],
      direccion: ['', Validators.required],
      detalle_dir: ['', Validators.required],
    });
  }

  login_create() {
    if (this.registerForm.invalid) return;

    const body = {
      shippingDetailsName: 'principal',
      name: this.userData.name,
      lastName: this.userData.lastName,
      address1: this.registerForm.value.direccion,
      address2: this.registerForm.value.detalle_dir,
      postalCode: this.registerForm.value.postal,
      provincia: this.registerForm.value.provincia,
      city: this.registerForm.value.ciudad,
      country: this.registerForm.value.pais,
      active: true,
      primaryAddress: true,
      gift: false,
    };

    const token = this.cookieService.get('accessToken');
    this.userService.addShipingDetails(body, token).subscribe((res) => {
      localStorage.setItem('userData', JSON.stringify(res));
    });
  }

  click_regreso() {
    this.regreso.emit(false);
  }
}
