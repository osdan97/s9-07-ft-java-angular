import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-navbar-categories',
  templateUrl: './navbar-categories.component.html',
  styleUrls: ['./navbar-categories.component.scss'],
})
export class NavbarCategoriesComponent {
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
}
