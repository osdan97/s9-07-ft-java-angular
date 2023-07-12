import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import {
  FormLoginInput,
  FormRegisterInput,
  LoginResponse,
  RegisterResponse,
} from '../../interfaces/auth.interfaces';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAutenticate = signal(false);
  private readonly baseUrl = environment.apiUrl;

  private readonly http = inject(HttpClient);

  login(form: FormLoginInput): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      `${this.baseUrl}authentication/sign-in`,
      form
    );
  }

  register(form: FormRegisterInput): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(
      `${this.baseUrl}authentication/sign-up`,
      form
    );
  }

  setAutenticate(value: boolean) {
    this.isAutenticate.set(value);
  }

  getAutenticate() {
    return this.isAutenticate();
  }
}
