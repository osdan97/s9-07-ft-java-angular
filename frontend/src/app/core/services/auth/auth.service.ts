import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import {
  FormLoginInput,
  FormRegisterInput,
  LoginResponse,
  RegisterResponse,
  UserData,
} from '../../interfaces/auth.interfaces';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAutenticate: BehaviorSubject<boolean> =
    new BehaviorSubject<boolean>(false);
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

  verificationAccount(token: string): Observable<string> {
    return this.http.get<string>(
      `${this.baseUrl}authentication/verify/${token}`
    );
  }

  renewSession(accessToken: string): Observable<UserData> {
    const headers = new HttpHeaders().set(
      'Authorization',
      `Bearer ${accessToken}`
    );
    return this.http.get<UserData>(`${this.baseUrl}authentication`, {
      headers,
    });
  }

  getAutenticate(): Observable<boolean> {
    return this.isAutenticate.asObservable();
  }

  setAutenticate(value: boolean): void {
    this.isAutenticate.next(value);
  }
}
