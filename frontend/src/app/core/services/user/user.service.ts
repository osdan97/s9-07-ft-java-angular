import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import {
  FormShippingDetail,
  ShippingDetailResponse,
} from '../../interfaces/user.interfaces';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly baseUrl = environment.apiUrl;

  private readonly http = inject(HttpClient);

  addShipingDetails(
    form: FormShippingDetail,
    userId: string
  ): Observable<ShippingDetailResponse> {
    return this.http.post<ShippingDetailResponse>(
      `${this.baseUrl}shipping-details/${userId}`,
      form
    );
  }
}
