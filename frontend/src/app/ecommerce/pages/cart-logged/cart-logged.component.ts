import { Component, OnInit, inject, signal } from '@angular/core';
import { CartProduct } from 'src/app/core/interfaces/products.interfaces';
import { CartService } from 'src/app/core/services/cart/cart.service';

@Component({
  selector: 'app-cart-logged',
  templateUrl: './cart-logged.component.html',
  styleUrls: ['./cart-logged.component.scss'],
})
export class CartLoggedComponent implements OnInit {
  cartProduct = signal<CartProduct[]>([]);
  totalPriceProduct = 0;
  totalProductToCart = 0;
  totalProductWeight = 0;

  private cartService = inject(CartService);

  ngOnInit(): void {
    this.getCart();
  }

  getCart() {
    const products = this.cartService.getCart();
    this.cartProduct.set(products);
    this.totalPrice();
    this.totalQuantity();
    this.totalWeight();
  }

  totalPrice() {
    let total = 0;
    this.cartProduct().forEach((product) => {
      total += product.price * product.quantity;
    });

    this.totalPriceProduct = total;
  }

  totalQuantity() {
    const totalProductos = this.cartProduct().reduce(
      (total, producto) => total + producto.quantity,
      0
    );

    this.totalProductToCart = totalProductos;
  }

  totalWeight() {
    const totalProductos = this.cartProduct().reduce(
      (total, producto) => total + producto.weight,
      0
    );

    this.totalProductWeight = totalProductos;
  }
}
