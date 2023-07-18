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
      image: '../../../../../../assets/images/argentina.svg',
    },
    {
      name: 'mexico',
      image: '../../../../../../assets/images/mexico.png',
    },
    {
      name: 'colombia',
      image: '../../../../../../assets/images/colombia.svg',
    },
    {
      name: 'venezuela',
      image: '../../../../../../assets/images/venezuela.png',
    },
  ]);
}
