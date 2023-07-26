import { Component, Input, inject, signal } from '@angular/core';
import { Message } from 'primeng/api';
import {
  CartProduct,
  ProductById,
} from 'src/app/core/interfaces/products.interfaces';
import { Product } from 'src/app/core/interfaces/user.interfaces';
import { CartService } from 'src/app/core/services/cart/cart.service';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.scss'],
})
export class DetalleProductoComponent {
  @Input() product!: CartProduct | any;

  quantity = signal<number>(1);

  messages!: Message[];

  cartService = inject(CartService);

  showQuantityProduc(product: any) {
    this.messages = [
      {
        severity: 'success',
        summary: `${product.name}`,
        detail: 'ha sido añadido exitosamente a tu carrito',
      },
    ];

    setTimeout(() => {
      this.messages = [];
    }, 8000);
  }

  payment() {
    const showQuantity = this.quantity();
    const product = this.product;
    const body = {
      id: product.id,
      name: product.name,
      image: product.image,
      price: product.price,
      stock: product.stock,
      weight: product.weight,
      quantity: showQuantity,
    };

    this.cartService.addToCart(body);
  }

  getQuantity(quantity: number): void {
    this.quantity.set(quantity);
  }
}
