import {
  Component,
  EventEmitter,
  Input,
  Output,
  signal,
  OnInit,
  inject,
} from '@angular/core';
import { CartProduct } from 'src/app/core/interfaces/products.interfaces';
import { CartService } from 'src/app/core/services/cart/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  @Output() visibleCart = new EventEmitter<boolean>();

  // @Input() cartProducts!: CartProduct[];
  @Input() changeButton = true;
  @Input() showBorder = false;
  @Input() isLogued = false;
  @Input() changeWidth = false;
  @Input() showCross = true;
  @Input() showTile = false;
  @Input() isHidden = false;
  @Input() details = true;
  @Input() disableShadow = false;

  cartProduct = signal<CartProduct[]>([]);
  totalPriceProduct = 0;
  totalProductToCart = 0;

  boton1 = 'Ver detalle';
  boton2 = 'Finalizar compra';

  cartService = inject(CartService);

  ngOnInit(): void {
    this.getCart();
  }

  estiloActivo = { boxShadow: 'none' };
  estiloInactivo = {};

  emitirValor() {
    this.visibleCart.emit(false);
  }

  backHistory() {
    window.history.back();
  }

  getCart() {
    const products = this.cartService.getCart();
    this.cartProduct.set(products);
    this.totalPrice();
    this.totalQuantity();
  }

  totalPrice() {
    let total = 0;
    this.cartProduct().forEach((product) => {
      total += product.price * product.quantity;
    });

    this.totalPriceProduct = total;
  }

  deleteItemCart(id: string) {
    this.cartService.removeFromCart(id);
    this.getCart();
  }

  totalQuantity() {
    const totalProductos = this.cartProduct().reduce(
      (total, producto) => total + producto.quantity,
      0
    );

    this.totalProductToCart = totalProductos;
  }
}
