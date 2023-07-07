import { Component, Input, OnInit } from '@angular/core';

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
  @Input() products!: any[];

  responsiveOptions!: ResponsiveOptions[];

  ngOnInit() {
    this.responsiveOptions = [
      {
        breakpoint: '1199px',
        numVisible: 3,
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
