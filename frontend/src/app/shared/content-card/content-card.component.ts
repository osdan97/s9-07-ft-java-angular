import { Component, Input, inject } from '@angular/core';
import { DataService } from 'src/app/core/services/data/data.service';

interface PageEvent {
  first: number;
  rows: number;
  page: number;
  pageCount: number;
}

@Component({
  selector: 'app-content-card',
  templateUrl: './content-card.component.html',
  styleUrls: ['./content-card.component.scss'],
})
export class ContentCardComponent {
  @Input() products!: any[];

  dataSerivice = inject(DataService);

  onPageChange(event: any) {
    const page = event.page + 1;

    this.getDataProduct(page);
  }

  getDataProduct(page: number) {
    this.dataSerivice.getProducts(page).subscribe((res) => {
      this.products = res;
    });
  }
}
