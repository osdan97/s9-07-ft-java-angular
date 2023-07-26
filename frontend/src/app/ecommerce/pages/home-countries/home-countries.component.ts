import { Component, signal, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { take } from 'rxjs';
import { ProductsResponse } from 'src/app/core/interfaces/products.interfaces';
import { DataService } from 'src/app/core/services/data/data.service';

@Component({
  selector: 'app-home-countries',
  templateUrl: './home-countries.component.html',
  styleUrls: ['./home-countries.component.scss'],
})
export class HomeCountriesComponent implements OnInit {
  products = signal<ProductsResponse[]>([]);

  country = undefined;
  totalPageNumber!: number;
  image = '';
  banner = '';

  dataSerivice = inject(DataService);
  route = inject(ActivatedRoute);

  ngOnInit(): void {
    this.getProductId();
  }

  getDataProduct(country: string) {
    const data = JSON.parse(sessionStorage.getItem('products') || '[]');
    this.dataSerivice.getProducts2(1, country).subscribe((res) => {
      this.products.set(res);
    });
    if (!data) return;
    this.products.set(data);
  }

  getProductId() {
    this.route.params.subscribe((res: Params) => {
      const countryMap: { [key: string]: string } = {
        arg: 'Argentina',
        col: 'Colombia',
        mex: 'Mexico',
        ven: 'Venezuela',
        // Agrega aquí más opciones si es necesario
      };

      const country = countryMap[res['country']] || res['country'];

      this.country = country;

      this.getDataProduct(country);
      this.getTotalPages(1, country);
      this.image = `../../../../assets/images/banner-${res['country']}.jpg`;
      this.banner = `../../../../assets/images/portada-${res['country']}.jpg`;
    });
  }

  getTotalPages(page: number, country?: string) {
    this.dataSerivice
      .getTotalPages(page, country)
      .pipe(take(1))
      .subscribe((res) => {
        console.log(res);
        this.totalPageNumber = res;
      });
  }
}
