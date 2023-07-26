import { Component, Input, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
export class ContentCardComponent implements OnInit {
  @Input() products!: any[];

  first = 0;

  dataSerivice = inject(DataService);
  router = inject(Router);

  ngOnInit(): void {
    this.first = JSON.parse(sessionStorage.getItem('page') || '0');
  }

  onPageChange(event: any) {
    const page = event.page + 1;

    if (sessionStorage.getItem('page') !== event.page.toString()) {
      sessionStorage.setItem('page', event.page.toString());
    }

    if (this.router.url === '/') {
      this.router.navigate(['products']);
    }

    this.getDataProduct(page);
  }

  getDataProduct(page: number) {
    this.dataSerivice.getProducts2(page).subscribe((res) => {
      this.products = res;
    });
  }
}
