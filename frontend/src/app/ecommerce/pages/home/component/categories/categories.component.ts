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
      image: 'assets/images/argentina.png',
    },
    {
      name: 'mexico',
      image: 'assets/images/argentina.png',
    },
    {
      name: 'colombia',
      image: 'assets/images/argentina.png',
    },
    {
      name: 'venezuela',
      image: 'assets/images/argentina.png',
    },
  ]);
}
