import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { CheckoutResponse, FormCheckout } from '../../interfaces/checkout';

@Injectable({
  providedIn: 'root',
})
export class CheckoutService {
  private readonly baseUrl = environment.apiUrl;

  http = inject(HttpClient);

  addOrder(form: any, token: string): Observable<CheckoutResponse> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.post<CheckoutResponse>(`${this.baseUrl}orders`, form, {
      headers,
    });
  }

  addPayment(form: any, token: string): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<any>(`${this.baseUrl}pay`, form, { headers });
  }
}
