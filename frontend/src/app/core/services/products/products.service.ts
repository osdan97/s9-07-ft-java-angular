import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private readonly baseUrl = environment.apiUrl;
  private dataUrl = '../../../assets/data/data.json';

  http = inject(HttpClient);

  getData(): Observable<any[]> {
    return this.http.get<any[]>(this.dataUrl);
  }

  searchProduct(product: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}products/name/${product}`);
  }
}
