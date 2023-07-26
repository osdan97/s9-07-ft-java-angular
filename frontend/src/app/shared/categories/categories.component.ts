import { Component, OnInit, inject, signal } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
})
export class CategoriesComponent implements OnInit {
  routeLink = '';

  categories = signal([
    {
      name: 'argentina',
      image: '../../../../../../assets/images/argentina.jpg',
      rute: 'arg',
    },
    {
      name: 'colombia',
      image: '../../../../../../assets/images/colombia.svg',
      rute: 'col',
    },
    {
      name: 'mexico',
      image: '../../../../../../assets/images/mexico.svg',
      rute: 'mex',
    },
    {
      name: 'venezuela',
      image: '../../../../../../assets/images/venezuela.svg',
      rute: 'ven',
    },
  ]);

  router = inject(Router);

  ngOnInit(): void {
    const currentUrl = this.router.url;
    const productRoute = currentUrl.split('/')[1];

    if (this.router.url === '/products') {
      this.routeLink = '../products/';
    } else {
      this.routeLink = '/products/';
    }
  }
}
