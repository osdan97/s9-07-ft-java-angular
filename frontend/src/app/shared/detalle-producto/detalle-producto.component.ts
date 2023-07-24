import { Component, Input } from '@angular/core';
import { Message } from 'primeng/api';
import {
  CartProduct,
  ProductById,
} from 'src/app/core/interfaces/products.interfaces';
import { Product } from 'src/app/core/interfaces/user.interfaces';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.scss'],
})
export class DetalleProductoComponent {
  messages!: Message[];
  @Input() product!: CartProduct | any;

  showQuantityProduc(product: any) {
    console.log(product);
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
}
