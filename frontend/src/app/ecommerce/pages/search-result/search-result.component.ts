import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/core/interfaces/user.interfaces';
import { ProductsService } from 'src/app/core/services/products/products.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss'],
})
export class SearchResultComponent implements OnInit {
  params = signal<string>('');
  products = signal<Product[]>([]);

  route = inject(ActivatedRoute);
  productService = inject(ProductsService);

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      const queryParamQ = params['q'];
      this.params.set(queryParamQ);

      this.productService.searchProduct(queryParamQ).subscribe((res) => {
        this.products.set(res);
        console.log(res.length);
      });
    });
  }
}
