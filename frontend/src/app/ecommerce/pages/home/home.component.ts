import { Component, signal, OnInit, inject } from '@angular/core';
import { ProductsResponse } from 'src/app/core/interfaces/products.interfaces';
import { DataService } from 'src/app/core/services/data/data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  products = signal<ProductsResponse[]>([]);

  dataSerivice = inject(DataService);

  ngOnInit(): void {
    this.getDataProduct();
  }

  getDataProduct() {
    const data = JSON.parse(sessionStorage.getItem('products') || '[]');
    this.dataSerivice.getDataProducts().subscribe((res) => {
      this.products.set(res);
    });
    if (!data) return;
    this.products.set(data);
  }
}
