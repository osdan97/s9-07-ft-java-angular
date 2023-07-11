import { Component, EventEmitter, Output, signal } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent {
  @Output() visibleCart = new EventEmitter<boolean>();
  boton1 = "Ver detalle";
  boton2 = "Finalizar compra";

  cart = signal<any>([
    {
      id: 1,
      name: 'Lorem ipsum kafkjbasblnnadlnsakn',
      price: 5,
      quantity: 1,
      image: 'https://via.placeholder.com/150',
    },
    {
      id: 2,
      name: 'Lorem ipsum kafkjbasblnnadlnsakn',
      price: 3,
      quantity: 1,
      image: 'https://via.placeholder.com/150',
    },
  ]);

  emitirValor() {
    this.visibleCart.emit(false);
  }



}