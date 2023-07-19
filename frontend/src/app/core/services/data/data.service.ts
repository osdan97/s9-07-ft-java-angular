import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import {
  FormProducts,
  ProductsResponse,
} from '../../interfaces/products.interfaces';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private readonly baseUrl = environment.apiUrl;

  private readonly http = inject(HttpClient);

  getProducts(
    page: number,
    country?: string,
    category?: string
  ): Observable<ProductsResponse> {
    let params = new HttpParams().set('page', page.toString());

    if (country) {
      params = params.set('page', page);
    }

    if (category) {
      params = params.set('page', category);
    }

    return this.http.get<ProductsResponse>(`${this.baseUrl}products/list`, {
      params,
    });
  }
}
