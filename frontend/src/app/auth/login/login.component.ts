import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { take } from 'rxjs';
import { LoginResponse } from 'src/app/core/interfaces/auth.interfaces';
import { AuthService } from 'src/app/core/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  authService = inject(AuthService);
  formBuilder = inject(FormBuilder);
  cookieService = inject(CookieService);

  ngOnInit(): void {
    this.loginForm = this.initFormLogin();
  }

  initFormLogin(): FormGroup {
    return this.formBuilder.group({
      email: [
        '',
        [
          Validators.required,
          Validators.pattern(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/),
        ],
      ],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  auth() {
    // const { email, password } = this.loginForm.controls;
    // console.log(password?.errors?.['minlength']);
    if (this.loginForm.invalid) return;
    this.login();
  }

  login() {
    this.authService
      .login(this.loginForm.value)
      .pipe(take(1))
      .subscribe((resp: LoginResponse) => {
        console.log(resp);
        // this.cookieService.set('accessToken', resp.token);
      });
  }
}
