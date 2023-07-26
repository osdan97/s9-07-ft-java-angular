import { Component, Input, inject, OnInit } from '@angular/core';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';
import { take } from 'rxjs';
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
  @Input() country!: string | undefined;

  @Input() totalPageNumber!: number;

  first = 0;

  dataSerivice = inject(DataService);
  router = inject(Router);

  ngOnInit(): void {
    this.first = JSON.parse(sessionStorage.getItem('page') || '0');

    if (this.router.url === '/' || this.router.url === '/products') {
      this.getTotalPages(this.first + 1, this.country);
    }
  }

  onPageChange(event: any) {
    const page = event.page + 1;

    if (sessionStorage.getItem('page') !== event.page.toString()) {
      sessionStorage.setItem('page', event.page.toString());
    }

    if (this.router.url === '/') {
      this.router.navigate(['products']);
    }

    this.getDataProduct(page, this.country);
  }

  getDataProduct(page: number, country?: string) {
    this.dataSerivice.getProducts2(page, country).subscribe((res) => {
      this.products = res;
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
