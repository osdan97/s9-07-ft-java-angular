import { Component, OnInit, inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

import { AuthService } from 'src/app/core/services/auth/auth.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-form-person',
  templateUrl: './form-person.component.html',
  styleUrls: ['./form-person.component.scss'],
})
export class FormPersonComponent implements OnInit {
  userData!: any;
  phoneNumbers: string[] = [];
  visible = false;

  registerForm!: FormGroup;

  formBuilder = inject(FormBuilder);
  authService = inject(AuthService);
  userService = inject(UserService);
  cookieService = inject(CookieService);

  ngOnInit(): void {
    this.getUserData();
    this.registerForm = this.initRegisterForm();
  }

  initRegisterForm(): FormGroup {
    return this.formBuilder.group({
      name: [this.userData.name, Validators.required],
      lastname: [this.userData.lastName, Validators.required],
      countryCodePrimary: [
        this.userData.phonesList[0].countryCode,
        Validators.required,
      ],
      cityCodePrimary: [
        this.userData.phonesList[0].cityCode,
        Validators.required,
      ],
      phoneNumberPrimary: [
        this.userData.phonesList[0].phoneNumber,
        Validators.required,
      ],
      countryCodeSecondary: [''],
      cityCodeSecondary: [''],
      phoneNumberSecondary: [''],
    });
  }

  getUserData() {
    this.userData = JSON.parse(localStorage.getItem('userData') || '{}');
  }

  updatePersonalData() {
    const body = {
      name: this.registerForm.value.name,
      lastName: this.registerForm.value.lastname,
      phonesList: [
        {
          phoneLabel: 'HOME',
          phoneNumber: this.registerForm.value.phoneNumberPrimary,
          cityCode: this.registerForm.value.cityCodePrimary,
          countryCode: this.registerForm.value.countryCodePrimary,
        },
        // {
        //   phoneLabel: 'SECUANDARY',
        //   phoneNumber: this.registerForm.value.phoneUuid,
        //   cityCode: this.registerForm.value.cityCodeSecondary,
        //   countryCode: this.registerForm.value.countryCodeSecondary,
        // },
      ],
    };

    const token = this.cookieService.get('accessToken');
    this.userService.addUserData(body, token).subscribe((res) => {
      console.log(res);
    });
  }
}
