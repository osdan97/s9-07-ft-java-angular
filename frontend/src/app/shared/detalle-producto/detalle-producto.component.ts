import { Component, Input } from '@angular/core';
import { Message } from 'primeng/api';
import { ProductById } from 'src/app/core/interfaces/products.interfaces';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.scss'],
})
export class DetalleProductoComponent {
  messages!: Message[];
  @Input() product!: ProductById;

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
