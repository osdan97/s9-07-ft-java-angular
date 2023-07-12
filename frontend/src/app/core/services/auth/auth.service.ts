import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
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
  // private isAutenticate = signal(false);
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

  renewSession(accessToken: string): Observable<UserData> {
    const headers = new HttpHeaders().set(
      'Authorization',
      `Bearer ${accessToken}`
    );
    return this.http.get<UserData>(`${this.baseUrl}authentication`, {
      headers,
    });
  }

  // setAutenticate(value: boolean) {
  //   this.isAutenticate.set(value);
  // }

  // getAutenticate() {
  //   return this.isAutenticate();
  // }

  getAutenticate(): Observable<boolean> {
    return this.isAutenticate.asObservable();
  }

  setAutenticate(value: boolean): void {
    this.isAutenticate.next(value);
  }
}
