import { Component, OnInit, signal } from '@angular/core';

interface ResponsiveOptions {
  breakpoint: string;
  numVisible: number;
  numScroll: number;
}

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss'],
})
export class NewsComponent implements OnInit {
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
