import { Component, Input, OnInit, signal } from '@angular/core';

interface ResponsiveOptions {
  breakpoint: string;
  numVisible: number;
  numScroll: number;
}

@Component({
  selector: 'app-slider-products',
  templateUrl: './slider-products.component.html',
  styleUrls: ['./slider-products.component.scss'],
})
export class SliderProductsComponent implements OnInit {
  @Input() title!: string;
  // @Input() products!: any[];

  products = signal([
    {
      name: 'product 1',
      image: 'assets/images/argentina.png',
    },
    {
      name: 'product 2',
      image: 'assets/images/argentina.png',
    },
    {
      name: 'product 3',
      image: 'assets/images/argentina.png',
    },
    {
      name: 'product 4',
      image: 'assets/images/argentina.png',
    },
    {
      name: 'product 5',
      image: 'assets/images/argentina.png',
    },
    {
      name: 'product 6',
      image: 'assets/images/argentina.png',
    },
  ]);

  responsiveOptions!: ResponsiveOptions[];

  ngOnInit() {
    this.responsiveOptions = [
      {
        breakpoint: '1199px',
        numVisible: 1,
        numScroll: 1,
      },
      {
        breakpoint: '991px',
        numVisible: 2,
        numScroll: 1,
      },
      {
        breakpoint: '767px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }
}
