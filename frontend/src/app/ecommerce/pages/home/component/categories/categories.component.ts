import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
})
export class CategoriesComponent {
  categories = signal([
    {
      name: 'argentina',
      image: '../../../../../../assets/images/argentina.svg',
    },
    {
      name: 'colombia',
      image: '../../../../../../assets/images/colombia.svg',
    },
    {
      name: 'mexico',
      image: '../../../../../../assets/images/mexico.svg',
    },
    {
      name: 'venezuela',
      image: '../../../../../../assets/images/venezuela.svg',
    },
  ]);
}
