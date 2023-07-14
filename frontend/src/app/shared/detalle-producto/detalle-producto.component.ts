import { Component } from '@angular/core';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.scss'],
})
export class DetalleProductoComponent {
  messages!: Message[];

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
    }, 3000);
  }
}
