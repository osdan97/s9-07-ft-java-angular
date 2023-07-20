import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { ProductsResponse } from '../../interfaces/products.interfaces';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private readonly baseUrl = environment.apiUrl;
  private dataProducts: BehaviorSubject<ProductsResponse[]> =
    new BehaviorSubject<ProductsResponse[]>([]);

  private readonly http = inject(HttpClient);

  getProducts(
    page: number,
    country?: string,
    category?: string
  ): Observable<ProductsResponse[]> {
    let params = new HttpParams().set('page', page.toString());

    if (country) {
      params = params.set('country', country);
    }

    if (category) {
      params = params.set('category', category);
    }

    return this.http
      .get<ProductsResponse[]>(`${this.baseUrl}products/list`, { params })
      .pipe(
        tap((products) => {
          this.dataProducts.next(products);
          sessionStorage.setItem('products', JSON.stringify(products));
        })
      );
  }

  getDataProducts(): Observable<ProductsResponse[]> {
    return this.dataProducts.asObservable();
  }
}
