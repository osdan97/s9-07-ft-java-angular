import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import {
  FavoriteResponse,
  FormShippingDetail,
  Product,
  ShippingDetailResponse,
} from '../../interfaces/user.interfaces';
import { BehaviorSubject, Observable, map, tap } from 'rxjs';
import jwtDecode from 'jwt-decode';
import { Payload } from '../../interfaces/auth.interfaces';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly baseUrl = environment.apiUrl;
  private favoritesSubject = new BehaviorSubject<Product[]>([]);
  public favorites$ = this.favoritesSubject.asObservable();

  private readonly http = inject(HttpClient);
  private readonly cookieService = inject(CookieService);

  constructor() {
    this.getFavoritesFromStorage();
  }

  private getFavoritesFromStorage() {
    const favorites = JSON.parse(sessionStorage.getItem('favorites') || '[]');
    this.favoritesSubject.next(favorites);
  }

  private updateFavoritesInStorage(favorites: Product[]) {
    sessionStorage.setItem('favorites', JSON.stringify(favorites));
  }

  private fetchFavoriteProducts(token: string): Observable<Product[]> {
    const accessToken: Payload = jwtDecode(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http
      .get<FavoriteResponse[]>(
        `${this.baseUrl}favorites/list/${accessToken.userId}`,
        {
          headers,
        }
      )

      .pipe(map((res) => res.map((item) => item.product)));
  }

  refreshFavorites(token: string): void {
    this.fetchFavoriteProducts(token).subscribe((favorites) => {
      this.favoritesSubject.next(favorites);
      this.updateFavoritesInStorage(favorites);
    });
  }

  addFavoriteProduct(token: string, product: string): Observable<Product> {
    const accessToken: Payload = jwtDecode(token);
    const body = {
      customers: accessToken.userId,
      product,
    };
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http
      .post<FavoriteResponse>(`${this.baseUrl}favorites/create`, body, {
        headers,
      })
      .pipe(
        tap((res) => {
          const currentFavorites = this.favoritesSubject.value;
          const updatedFavorites = [...currentFavorites, res.product];
          this.favoritesSubject.next(updatedFavorites);
          this.updateFavoritesInStorage(updatedFavorites);
        }),
        map((res) => res.product)
      );
  }

  addShipingDetails(
    form: FormShippingDetail,
    token: string
  ): Observable<ShippingDetailResponse> {
    const accessToken: Payload = jwtDecode(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.post<ShippingDetailResponse>(
      `${this.baseUrl}shipping-details/${accessToken.userId}`,
      form,
      { headers }
    );
  }

  addUserData(body: any, token: string): Observable<any> {
    const accessToken: Payload = jwtDecode(token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.put<any>(
      `${this.baseUrl}customer/update/${accessToken.userId}`,
      body,
      { headers }
    );
  }

  // getFavoriteProducts(token: string): Observable<Product[]> {
  //   const accessToken: Payload = jwtDecode(token);

  //   const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  //   return this.http
  //     .get<FavoriteResponse[]>(
  //       `${this.baseUrl}favorites/list/${accessToken.userId}`,
  //       {
  //         headers,
  //       }
  //     )
  //     .pipe(map((res) => res.map((item) => item.product)));
  // }

  // addFavoriteProducts(token: string, product: string): Observable<Product> {
  //   const accessToken: Payload = jwtDecode(token);

  //   const body = {
  //     customers: accessToken.userId,
  //     product,
  //   };

  //   const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  //   return this.http
  //     .post<FavoriteResponse>(`${this.baseUrl}favorites/create`, body, {
  //       headers,
  //     })
  //     .pipe(map((res) => res.product));
  // }
}
