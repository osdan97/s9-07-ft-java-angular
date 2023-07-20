import { Component, signal } from '@angular/core';


@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent {
  data = signal([
    {
      id: 1,
      title: 'Producto 1',
      description: 'Lorem ipsum dolor ',
      price: 100,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '125gr',
      quantity: 1,
    },
    {
      id: 2,
      title: 'Producto 2',
      description: 'Lorem ipsum dolor ',
      price: 5,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '300gr',
      quantity: 1,
    },
    {
      id: 3,
      title: 'Producto 3',
      description: 'Lorem ipsum dolor ',
      price: 400,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '1kg',
      quantity: 1,
    },
    {
      id: 4,
      title: 'Producto 4',
      description: 'Lorem ipsum dolor ',
      price: 20,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '425gr',
      quantity: 1,
    },
   /* {
      id: 5,
      title: 'Producto 5',
      description: 'Lorem ipsum dolor ',
      price: 150,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '1kg',
      quantity: 1,
    },
    {
      id: 6,
      title: 'Producto 6',
      description: 'Lorem ipsum dolor ',
      price: 60,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '168gr',
      quantity: 1,
    },
    {
      id: 7,
      title: 'Producto 7',
      description: 'Lorem ipsum dolor ',
      price: 60,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '168gr',
      quantity: 1,
    },
    {
      id: 8,
      title: 'Producto 8',
      description: 'Lorem ipsum dolor ',
      price: 60,
      image:
        'https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=600',
      stock: 'en stock',
      weight: '168gr',
      quantity: 1,
    },*/
  ]);

}
