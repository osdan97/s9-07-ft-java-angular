import { Component, signal, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {
  ProductById,
  ProductsResponse,
} from 'src/app/core/interfaces/products.interfaces';
import { DataService } from 'src/app/core/services/data/data.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.scss'],
})
export class ProductoComponent implements OnInit {
  products = signal<ProductsResponse[]>([]);
  product!: ProductById;

  dataSerivice = inject(DataService);
  router = inject(ActivatedRoute);

  ngOnInit(): void {
    this.getDataProduct();
    this.getProductId();
  }

  getProductId() {
    this.router.params.subscribe((res) => {
      const IdProductToken = res['id'];
      this.getProductById(IdProductToken);
    });
  }

  getProductById(id: string) {
    this.dataSerivice.getProductById(id).subscribe((res) => {
      this.product = res;
    });
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
