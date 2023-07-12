import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private dataUrl = '../../../assets/data/data.json';

  http = inject(HttpClient);

  getData(): Observable<any[]> {
    return this.http.get<any[]>(this.dataUrl);
  }
}
