import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  inject,
  signal,
} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  @Output() visibleCart = new EventEmitter<boolean>();
  @Input() changeButton = true;
  @Input() showBorder = false;

  boton1 = 'Ver detalle';
  boton2 = 'Finalizar compra';

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

  ngOnInit(): void {}

  emitirValor() {
    this.visibleCart.emit(false);
  }

  backHistory() {
    window.history.back();
  }
}
