import { Component, Input, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
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

  page = signal<number>(0);
  order = signal<string>('');

  selectForm!: FormGroup;

  first = 0;

  dataSerivice = inject(DataService);
  formBuilder = inject(FormBuilder);
  router = inject(Router);

  ngOnInit(): void {
    this.first = JSON.parse(sessionStorage.getItem('page') || '0');

    if (this.router.url === '/' || this.router.url === '/products') {
      this.getTotalPages(this.first + 1, this.country);
    }

    this.selectForm = this.initselectForm();
  }

  onPageChange(event: any) {
    const page = event.page + 1;

    if (sessionStorage.getItem('page') !== event.page.toString()) {
      sessionStorage.setItem('page', event.page.toString());
    }

    if (this.router.url === '/') {
      this.router.navigate(['products']);
    }

    this.page.set(page);

    this.getDataProduct(page, this.order(), this.country, undefined);
  }

  getDataProduct(
    page: number,
    sort: string,
    country?: string,
    category?: string
  ) {
    this.dataSerivice
      .getProducts2(page, sort, country, category)
      .subscribe((res) => {
        this.products = res;
        console.log(res);
      });
  }

  getTotalPages(page: number, country?: string) {
    this.dataSerivice
      .getTotalPages(page, country)
      .pipe(take(1))
      .subscribe((res) => {
        this.totalPageNumber = res;
      });
  }

  initselectForm(): FormGroup {
    return this.formBuilder.group({
      filter: [''],
    });
  }

  obtenerValorSeleccionado() {
    const page = this.page() + 1;

    const valorSeleccionado = this.selectForm.value.filter;
    this.order.set(valorSeleccionado);
    this.getDataProduct(page, valorSeleccionado, this.country, undefined);
  }
}
